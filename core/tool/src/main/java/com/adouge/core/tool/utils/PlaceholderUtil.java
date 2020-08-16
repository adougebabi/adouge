package com.adouge.core.tool.utils;

import java.util.Map;
import java.util.function.Function;

/**
 * @author : Vinson
 * @date : 2020/8/13 10:34 下午
 */
public class PlaceholderUtil {
    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";
    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";
    private static final PlaceholderUtil DEFAULT_RESOLVER = new PlaceholderUtil();
    private String placeholderPrefix = "${";
    private String placeholderSuffix = "}";

    private PlaceholderUtil() {
    }

    private PlaceholderUtil(String placeholderPrefix, String placeholderSuffix) {
        this.placeholderPrefix = placeholderPrefix;
        this.placeholderSuffix = placeholderSuffix;
    }
    public static PlaceholderUtil getDefaultResolver() {
        return DEFAULT_RESOLVER;
    }

    public static PlaceholderUtil getResolver(String placeholderPrefix, String placeholderSuffix) {
        return new PlaceholderUtil(placeholderPrefix, placeholderSuffix);
    }
    public String resolveByRule(String content, Function<String, String> rule) {
        int start = content.indexOf(this.placeholderPrefix);
        if (start == -1) {
            return content;
        } else {
            StringBuilder result;
            String replaceContent;
            for(result = new StringBuilder(content); start != -1; start = result.indexOf(this.placeholderPrefix, start + replaceContent.length())) {
                int end = result.indexOf(this.placeholderSuffix, start + 1);
                String placeholder = result.substring(start + this.placeholderPrefix.length(), end);
                replaceContent = placeholder.trim().isEmpty() ? "" : (String)rule.apply(placeholder);
                result.replace(start, end + this.placeholderSuffix.length(), replaceContent);
            }

            return result.toString();
        }
    }
    public String resolveByMap(String content, final Map<String, Object> valueMap) {
        return this.resolveByRule(content, (placeholderValue) -> {
            return String.valueOf(valueMap.get(placeholderValue));
        });
    }
}
