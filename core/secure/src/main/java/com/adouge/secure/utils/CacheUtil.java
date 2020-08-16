package com.adouge.secure.utils;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.lang.Nullable;

import java.lang.reflect.Field;
import java.util.concurrent.Callable;

/**
 * @author : Vinson
 * @date : 2020/8/13 1:44 下午
 */
public class CacheUtil {
    private static CacheManager cacheManager;
    private static final Boolean TENANT_MODE=Boolean.TRUE;


    private static CacheManager getCacheManager() {
        if (cacheManager == null) {
            cacheManager = (CacheManager) SpringUtil.getBean(CacheManager.class);
        }

        return cacheManager;
    }

    public static Cache getCache(String cacheName) {
        return getCache(cacheName, TENANT_MODE);
    }

    public static Cache getCache(String cacheName, Boolean tenantMode) {
        return getCacheManager().getCache(formatCacheName(cacheName, tenantMode));
    }

    public static String formatCacheName(String cacheName, Boolean tenantMode) {
        if (!tenantMode) {
            return cacheName;
        } else {
            String tenantId = SecureUtil.getTenantId();
            return StrUtil.isBlank(tenantId) ? cacheName : tenantId.concat(":").concat(cacheName);
        }
    }

    @Nullable
    public static Object get(String cacheName, String keyPrefix, Object key) {
        return get(cacheName, keyPrefix, key, TENANT_MODE);
    }

    @Nullable
    public static Object get(String cacheName, String keyPrefix, Object key, Boolean tenantMode) {
        if (StrUtil.isEmpty(cacheName)||StrUtil.isEmpty(keyPrefix)||ObjectUtil.isEmpty(key)) {
            return null;
        } else {
            Cache.ValueWrapper valueWrapper = getCache(cacheName, tenantMode).get(keyPrefix.concat(String.valueOf(key)));
            if (ObjectUtil.isEmpty(valueWrapper)) {
                return null;
            } else {
                assert valueWrapper != null;
                return valueWrapper.get();
            }
        }
    }

    @Nullable
    public static <T> T get(String cacheName, String keyPrefix, Object key, @Nullable Class<T> type) {
        return get(cacheName, keyPrefix, key, type, TENANT_MODE);
    }

    @Nullable
    public static <T> T get(String cacheName, String keyPrefix, Object key, @Nullable Class<T> type, Boolean tenantMode) {
        return StrUtil.isEmpty(cacheName)||StrUtil.isEmpty(keyPrefix)||ObjectUtil.isEmpty(key) ? null : getCache(cacheName, tenantMode).get(keyPrefix.concat(String.valueOf(key)), type);
    }

    @Nullable
    public static <T> T get(String cacheName, String keyPrefix, Object key, Callable<T> valueLoader) {
        return get(cacheName, keyPrefix, key, valueLoader, TENANT_MODE);
    }

    @Nullable
    public static <T> T get(String cacheName, String keyPrefix, Object key, Callable<T> valueLoader, Boolean tenantMode) {
        if (StrUtil.isEmpty(cacheName)||StrUtil.isEmpty(keyPrefix)||ObjectUtil.isEmpty(key)) {
            return null;
        } else {
            try {
                Cache.ValueWrapper valueWrapper = getCache(cacheName, tenantMode).get(keyPrefix.concat(String.valueOf(key)));
                Object value = null;
                if (valueWrapper == null) {
                    T call = valueLoader.call();
                    if (ObjectUtil.isNotEmpty(call)) {
                        Field field = ReflectUtil.getField(call.getClass(), "id");
                        if (ObjectUtil.isNotEmpty(field) && ObjectUtil.isEmpty(ClassUtil.getPublicMethod(call.getClass(),"getId").invoke(call))) {
                            return null;
                        }

                        getCache(cacheName, tenantMode).put(keyPrefix.concat(String.valueOf(key)), call);
                        value = call;
                    }
                } else {
                    value = valueWrapper.get();
                }

                return (T) value;
            } catch (Exception var9) {
                var9.printStackTrace();
                return null;
            }
        }
    }

    public static void put(String cacheName, String keyPrefix, Object key, @Nullable Object value) {
        put(cacheName, keyPrefix, key, value, TENANT_MODE);
    }

    public static void put(String cacheName, String keyPrefix, Object key, @Nullable Object value, Boolean tenantMode) {
        getCache(cacheName, tenantMode).put(keyPrefix.concat(String.valueOf(key)), value);
    }

    public static void evict(String cacheName, String keyPrefix, Object key) {
        evict(cacheName, keyPrefix, key, TENANT_MODE);
    }

    public static void evict(String cacheName, String keyPrefix, Object key, Boolean tenantMode) {
        if (!(StrUtil.isEmpty(cacheName)||StrUtil.isEmpty(keyPrefix)||ObjectUtil.isEmpty(key))) {
            getCache(cacheName, tenantMode).evict(keyPrefix.concat(String.valueOf(key)));
        }
    }

    public static void clear(String cacheName) {
        clear(cacheName, TENANT_MODE);
    }

    public static void clear(String cacheName, Boolean tenantMode) {
        if (!StrUtil.isEmpty(cacheName)) {
            getCache(cacheName, tenantMode).clear();
        }
    }
}
