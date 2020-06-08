package com.adouge.core.tool.support;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.util.HashMap;

/**
 * @author : Vinson
 * @date : 2020/5/25 10:38 下午
 */
public class ParamMap extends LinkedCaseInsensitiveMap<Object> {
    public ParamMap() {
    }

    /**
     * 初始化
     *
     * @return LinkedCaseInsensitiveMap
     */
    public static ParamMap init() {
        return new ParamMap();
    }

    /**
     * 初始化
     *
     * @return HashMap
     */
    public static HashMap<?, ?> newMap() {
        return MapUtil.newHashMap(16);
    }

    /**
     * 设置参数
     *
     * @param k key
     * @param v value
     * @return ParamMap
     */
    public ParamMap set(String k, Object v) {
        this.put(k, v);
        return this;
    }

    /**
     * 设置参数 忽略null
     *
     * @param k key
     * @param v value
     * @return ParamMap
     */
    public ParamMap setIgnoreNull(String k, String v) {
        if (ObjectUtil.isNotNull(k) && ObjectUtil.isNotNull(v)) {
            return set(k, v);
        }
        return this;
    }

    /**
     * 获取参数
     *
     * @param k            key
     * @param defaultValue 默认值
     * @param <T>          值类型
     * @return 值
     */
    public <T> T get(String k, T defaultValue) {
        Object result = get(k);
        return (T) (result != null ? result : defaultValue);
    }

    /**
     * 获取String
     *
     * @param k key
     * @return String/null
     */
    public String getStr(String k) {
        return get(k, null);
    }

    /**
     * 获取Int
     * @param k key
     * @return int/-1
     */
    public Integer getInt(String k) {
        return get(k, -1);
    }

    @Override
    public ParamMap clone() {
        return (ParamMap) super.clone();
    }
}
