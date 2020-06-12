package com.adouge.core.mybatis.support;

import cn.hutool.core.util.ObjectUtil;
import com.adouge.core.launch.constant.TokenConstant;
import com.adouge.core.tool.support.ParamMap;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 条件构造器
 *
 * @author : Vinson
 * @date : 2020/6/5 5:01 下午
 */
public class Condition {
    /**
     * 转化成mybatis plus中的Page
     *
     * @param query 查询条件
     * @return IPage
     */
    public static <T> IPage<T> getPage(Query query) {
        Page<T> page = new Page<>(query.getCurrent(), query.getSize());
        List<OrderItem> orderItemList = new ArrayList<>();
        if (ObjectUtil.isNotNull(query.getAscs())) {
            orderItemList.addAll(OrderItem.ascs(SqlKeyword.filter(query.getAscs()).split(",")));
        }
        if (ObjectUtil.isNotNull(query.getDescs())) {
            orderItemList.addAll(OrderItem.descs(SqlKeyword.filter(query.getDescs()).split(",")));
        }
        page.setOrders(orderItemList);
        return page;
    }

    /**
     * 获取mybatis plus中的QueryWrapper
     *
     * @param entity 实体
     * @param <T>    类型
     * @return QueryWrapper
     */
    public static <T> QueryWrapper<T> getQueryWrapper(T entity) {
        return new QueryWrapper<>(entity);
    }

    /**
     * 获取mybatis plus中的QueryWrapper
     *
     * @param query 查询条件
     * @param clazz 实体类
     * @param <T>   类型
     * @return QueryWrapper
     */
    public static <T> QueryWrapper<T> getQueryWrapper(Map<String, Object> query, Class<T> clazz) {
        ParamMap exclude = ParamMap.init().set(TokenConstant.HEADER, TokenConstant.HEADER)
                .set("current", "current").set("size", "size").set("ascs", "ascs").set("descs", "descs");
        return getQueryWrapper(query, exclude, clazz);
    }

    /**
     * 获取mybatis plus中的QueryWrapper
     *
     * @param query   查询条件
     * @param exclude 排除的查询条件
     * @param clazz   实体类
     * @param <T>     类型
     * @return QueryWrapper
     */
    public static <T> QueryWrapper<T> getQueryWrapper(Map<String, Object> query, Map<String, Object> exclude, Class<T> clazz) {
        exclude.forEach((k, v) -> query.remove(k));
        QueryWrapper<T> qw = new QueryWrapper<>();
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (Exception e) {

        }
        qw.setEntity(t);
        SqlKeyword.buildCondition(query, qw);
        return qw;
    }
}
