package com.adouge.gateway.sentinel;

import java.util.regex.Pattern;

/**
 * @author : Vinson
 * @date : 2020/5/27 10:15 下午
 */
public class RestfulPattern implements Comparable<RestfulPattern> {
    private final Pattern pattern;
    private final String realResource;

    public RestfulPattern(Pattern pattern, String realResource) {
        this.pattern = pattern;
        this.realResource = realResource;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public String getRealResource() {
        return realResource;
    }

    @Override
    public int compareTo(RestfulPattern o) {
        return o.getPattern().pattern().compareTo(this.getPattern().pattern());
    }
}
