package com.adouge.gateway.sentinel;


import cn.hutool.core.util.ObjectUtil;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.UrlCleaner;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Vinson
 * @date : 2020/5/27 10:16 下午
 */
public class RestfulUrlCleaner implements UrlCleaner {

    private static final List<RestfulPattern> patterns = new ArrayList<>();
    private static final String PATTERN = "\\{[^\\}]+\\}";

    private RestfulUrlCleaner() {
    }

    /**
     * 根据流量控制规则创建与之匹配的RestfulUrlCleaner
     *
     * @param rules 流量控制规则
     * @return RestfulUrlCleaner
     */
    public static RestfulUrlCleaner create(List<FlowRule> rules) {
        RestfulUrlCleaner restfulUrlCleaner = new RestfulUrlCleaner();
        if (rules == null || rules.size() == 0) {
            return restfulUrlCleaner;
        }
        Pattern p = Pattern.compile(PATTERN);
        for (FlowRule rule : rules) {
            Matcher m = p.matcher(rule.getResource());
            //如果发现类似{xxx}的结构，断定其为RESTful接口
            if (m.find()) {
                RestfulPattern restfulPattern = new RestfulPattern(Pattern.compile(m.replaceAll("\\\\S+?")), rule.getResource());
                if (ObjectUtil.isNotNull(restfulPattern)&& !patterns.contains(restfulPattern)) {
                    patterns.add(restfulPattern);
                }
            }
        }
        Collections.sort(patterns);
        return restfulUrlCleaner;
    }

    @Override
    public String clean(String originUrl) {
        for (RestfulPattern pattern : patterns) {
            if (pattern.getPattern().matcher(originUrl).matches()) {
                return pattern.getRealResource();
            }
        }
        return originUrl;
    }
}
