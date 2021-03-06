package com.adouge.core.develop.support;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.PostgreSqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.Data;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author : Vinson
 * @date : 2020/6/9 5:17 下午
 */
@Data
public class CodeGenerator {
    /**
     * 代码模块名称
     */
    private String codeName;
    /**
     * 代码所在服务名
     */
    private String serviceName = "adouge-service";
    /**
     * 代码生成的包名
     */
    private String packageName = "com.adouge.test";
    /**
     * 代码后端生成的地址
     */
    private String packageDir;
    /**
     * 代码前端生成的地址
     */
    private String packageWebDir;
    /**
     * 需要去掉的表前缀
     */
    private String[] tablePrefix = {"adouge_"};
    /**
     * 需要生成的表名(两者只能取其一)
     */
    private String[] includeTables = {"adouge_test"};
    /**
     * 需要排除的表名(两者只能取其一)
     */
    private String[] excludeTables = {};
    /**
     * 是否包含基础业务字段
     */
    private Boolean hasSuperEntity = Boolean.FALSE;
    /**
     * 是否包含包装器
     */
    private Boolean hasWrapper = Boolean.FALSE;
    /**
     * 基础业务字段
     */
    private String[] superEntityColumns = {"created_time", "created_by", "created_dept", "updated_time", "updated_by", "status", "is_deleted"};
    /**
     * 租户字段
     */
    private String tenantColumn = "tenant_id";
    /**
     * 是否启用swagger
     */
    private Boolean isSwagger2 = Boolean.TRUE;
    /**
     * 数据库驱动名
     */
    private String driverName;
    /**
     * 数据库链接地址
     */
    private String url;
    /**
     * 数据库用户名
     */
    private String username;
    /**
     * 数据库密码
     */
    private String password;

    public void run() {
        Properties props = getProperties();
        AutoGenerator mpg = new AutoGenerator();
        GlobalConfig gc = new GlobalConfig();
        String outputDir = getOutputDir();
        String author = props.getProperty("author");
        gc.setOutputDir(outputDir);
        gc.setAuthor(author);
        gc.setFileOverride(true);
        gc.setOpen(false);
        gc.setActiveRecord(false);
        gc.setEnableCache(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("I%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);
        DataSourceConfig dsc = new DataSourceConfig();
        String driverName = getDriverName();
        if (StrUtil.containsAny(driverName, DbType.MYSQL.getDb())) {
            dsc.setDbType(DbType.MYSQL);
            dsc.setTypeConvert(new MySqlTypeConvert());
        } else if (StrUtil.containsAny(driverName, DbType.POSTGRE_SQL.getDb())) {
            dsc.setDbType(DbType.POSTGRE_SQL);
            dsc.setTypeConvert(new PostgreSqlTypeConvert());
        } else {
            dsc.setDbType(DbType.ORACLE);
            dsc.setTypeConvert(new OracleTypeConvert());
        }
        dsc.setDriverName(driverName);
        dsc.setUrl(getUrl());
        dsc.setUsername(getUsername());
        dsc.setPassword(getPassword());
        mpg.setDataSource(dsc);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名
        // strategy.setDbColumnUnderline(true);//全局下划线命名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setTablePrefix(getTablePrefix());
        if (getIncludeTables().length > 0) {
            strategy.setInclude(getIncludeTables());
        }
        if (getExcludeTables().length > 0) {
            strategy.setExclude(getExcludeTables());
        }
        if (getHasSuperEntity()) {
            strategy.setSuperEntityClass("com.adouge.core.mybatis.base.TenantEntity");
            strategy.setSuperEntityColumns(getSuperEntityColumns());
            strategy.setSuperServiceClass("com.adouge.core.mybatis.base.BaseService");
            strategy.setSuperServiceImplClass("com.adouge.core.mybatis.base.BaseServiceImpl");
        } else {
            strategy.setSuperServiceClass("com.baomidou.mybatisplus.extension.service.IService");
            strategy.setSuperServiceImplClass("com.baomidou.mybatisplus.extension.service.impl.ServiceImpl");
        }
        // 自定义 controller 父类
        strategy.setSuperControllerClass("com.adouge.boot.controller.BaseController");
        strategy.setEntityBuilderModel(false);
        strategy.setEntityLombokModel(true);
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);
        // 包配置
        PackageConfig pc = new PackageConfig();
        // 控制台扫描
        pc.setModuleName(null);
        pc.setParent(getPackageName());
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setXml("mapper");
        mpg.setPackageInfo(pc);
        mpg.setCfg(getInjectionConfig());
        mpg.execute();
    }

    private InjectionConfig getInjectionConfig() {
        String servicePackage = getServiceName().split("-").length > 1 ? getServiceName().split("-")[1] : getServiceName();
        // 自定义配置
        Map<String, Object> map = new HashMap<>(16);
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                map.put("codeName", getCodeName());
                map.put("serviceName", getServiceName());
                map.put("servicePackage", servicePackage);
                map.put("servicePackageLowerCase", servicePackage.toLowerCase());
                map.put("tenantColumn", getTenantColumn());
                map.put("hasWrapper", getHasWrapper());
                this.setMap(map);
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/sql/menu.sql.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                map.put("entityKey", (tableInfo.getEntityName().toLowerCase()));
                map.put("menuId", IdWorker.getId());
                map.put("addMenuId", IdWorker.getId());
                map.put("editMenuId", IdWorker.getId());
                map.put("removeMenuId", IdWorker.getId());
                map.put("viewMenuId", IdWorker.getId());
                return getOutputDir() + "/" + "/sql/" + tableInfo.getEntityName().toLowerCase() + ".menu.mysql";
            }
        });
        focList.add(new FileOutConfig("/templates/entityVO.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return getOutputDir() + "/" + getPackageName().replace(".", "/") + "/" + "vo" + "/" + tableInfo.getEntityName() + "VO" + StringPool.DOT_JAVA;
            }
        });
        focList.add(new FileOutConfig("/templates/entityDTO.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return getOutputDir() + "/" + getPackageName().replace(".", "/") + "/" + "dto" + "/" + tableInfo.getEntityName() + "DTO" + StringPool.DOT_JAVA;
            }
        });
        if (getHasWrapper()) {
            focList.add(new FileOutConfig("/templates/wrapper.java.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return getOutputDir() + "/" + getPackageName().replace(".", "/") + "/" + "wrapper" + "/" + tableInfo.getEntityName() + "Wrapper" + StringPool.DOT_JAVA;
                }
            });
        }
        if (StrUtil.isNotBlank(getPackageWebDir())) {
            focList.add(new FileOutConfig("/templates/web/api.js.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return getOutputWebDir() + "/api" + "/" + servicePackage.toLowerCase() + "/" + tableInfo.getEntityName().toLowerCase() + ".js";
                }
            });
            focList.add(new FileOutConfig("/templates/web/crud.vue.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return getOutputWebDir() + "/views" + "/" + servicePackage.toLowerCase() + "/" + tableInfo.getEntityName().toLowerCase() + ".vue";
                }
            });
            focList.add(new FileOutConfig("/templates/web/options.js.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return getOutputWebDir() + "/option" + "/" + servicePackage.toLowerCase() + "/" + tableInfo.getEntityName().toLowerCase() + ".js";
                }
            });
        }
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    /**
     * 获取配置文件
     *
     * @return 配置Props
     */
    private Properties getProperties() {
        // 读取配置文件
        Resource resource = new ClassPathResource("/templates/code.properties");
        Properties props = new Properties();
        try {
            props = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    /**
     * 生成到项目中
     *
     * @return outputDir
     */
    public String getOutputDir() {
        return (StrUtil.isBlank(getPackageDir()) ? System.getProperty("user.dir") + "/adouge-ops/adouge-develop" : getPackageDir()) + "/src/main/java";
    }

    /**
     * 生成到Web项目中
     *
     * @return outputDir
     */
    public String getOutputWebDir() {
        return (StrUtil.isBlank(getPackageWebDir()) ? System.getProperty("user.dir") : getPackageWebDir()) + "/src";
    }

    /**
     * 页面生成的文件名
     */
    private String getGeneratorViewPath(String viewOutputDir, TableInfo tableInfo, String suffixPath) {
        String name = StringUtils.firstToLowerCase(tableInfo.getEntityName());
        String path = viewOutputDir + "/" + name + "/" + name + suffixPath;
        File viewDir = new File(path).getParentFile();
        if (!viewDir.exists()) {
            viewDir.mkdirs();
        }
        return path;
    }
}
