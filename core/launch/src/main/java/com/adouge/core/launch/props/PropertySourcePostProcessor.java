package com.adouge.core.launch.props;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : Vinson
 * @date : 2020/6/12 10:11 上午
 */
@Slf4j
public class PropertySourcePostProcessor implements BeanFactoryPostProcessor, InitializingBean, Ordered {
    private final ResourceLoader resourceLoader = new DefaultResourceLoader();
    private final List<PropertySourceLoader> propertySourceLoaders = SpringFactoriesLoader.loadFactories(PropertySourceLoader.class, this.getClass().getClassLoader());

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("PropertySourcePostProcessor 初始化.");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.info("开始处理@AdougePropertySource的数据");
        Map<String, Object> beansWithAnnotation = beanFactory.getBeansWithAnnotation(AdougePropertySource.class);
        Set<Map.Entry<String, Object>> beanEntrySet = beansWithAnnotation.entrySet();
        if (beanEntrySet.isEmpty()) {
            log.warn("找不到@AdougePropertySource");
        } else {
            List<PropertySourcePostProcessor.PropertyFile> propertyFileList = new ArrayList<>();
            for (Map.Entry<String, Object> stringObjectEntry : beanEntrySet) {
                Class<?> beanClass = ClassUtils.getUserClass(stringObjectEntry.getValue());
                AdougePropertySource adougePropertySource = AnnotationUtils.getAnnotation(beanClass, AdougePropertySource.class);
                if (adougePropertySource != null) {
                    int order = adougePropertySource.order();
                    boolean loadActiveProfile = adougePropertySource.loadActiveProfile();
                    String location = adougePropertySource.value();
                    propertyFileList.add(new PropertyFile(order, location, loadActiveProfile));
                }
            }
            Map<String, PropertySourceLoader> loaderMap = new HashMap<>(16);
            String[] activeProfiles;

            propertySourceLoaders.forEach(loader->Arrays.stream(loader.getFileExtensions()).forEach(extension -> loaderMap.put(extension, loader)));

            List<PropertySourcePostProcessor.PropertyFile> sortedPropertyList = propertyFileList.stream().distinct().sorted().collect(Collectors.toList());
            ConfigurableEnvironment environment = beanFactory.getBean(ConfigurableEnvironment.class);
            MutablePropertySources propertySources = environment.getPropertySources();
            activeProfiles = environment.getActiveProfiles();
            ArrayList<PropertySource<?>> propertySourceList = new ArrayList<>();
            for (String profile : activeProfiles) {
                sortedPropertyList.forEach(propertyFile -> {
                    if (propertyFile.loadActiveProfile) {
                        String extension = propertyFile.getExtension();
                        PropertySourceLoader loader = loaderMap.get(extension);
                        if (loader == null) {
                            throw new IllegalArgumentException("Can't find PropertySourceLoader for PropertySource extension:" + extension);
                        }

                        String location = propertyFile.getLocation();
                        String filePath = StringUtils.stripFilenameExtension(location);
                        String profiledLocation = filePath + "-" + profile + "." + extension;
                        Resource resource = this.resourceLoader.getResource(profiledLocation);
                        loadPropertySource(profiledLocation, resource, loader, propertySourceList);
                    }
                });
            }
            sortedPropertyList.forEach(propertyFile -> {
                String extension = propertyFile.getExtension();
                PropertySourceLoader loader = loaderMap.get(extension);
                String location = propertyFile.getLocation();
                Resource resource = this.resourceLoader.getResource(location);
                loadPropertySource(location, resource, loader, propertySourceList);
            });

            propertySourceList.forEach(propertySources::addLast);
        }
    }

    private static void loadPropertySource(String location, Resource resource, PropertySourceLoader loader, List<PropertySource<?>> sourceList) {
        if (resource.exists()) {
            String name = "PropertySource: [" + location + "]";
            try {
                sourceList.addAll(loader.load(name, resource));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }

    @Data
    private static class PropertyFile {
        private final int order;
        private final String location;
        private final String extension;
        private final boolean loadActiveProfile;

        public PropertyFile(int order, String location, boolean loadActiveProfile) {
            this.order = order;
            this.location = location;
            this.loadActiveProfile = loadActiveProfile;
            this.extension = Objects.requireNonNull(StringUtils.getFilenameExtension(location));
        }
    }
}
