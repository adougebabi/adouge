package com.adouge.secure.registry;

import com.adouge.secure.props.AuthSecure;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/6/15 1:34 下午
 */
@Data
public class SecureRegistry {
    private boolean enabled = true;
    private boolean authEnabled = true;
    private boolean clientEnabled = true;
    private final List<String> defaultExcludePatterns = new ArrayList<>();
    private final List<String> excludePatterns = new ArrayList<>();
    private final List<AuthSecure> authSecures = new ArrayList<>();

    public SecureRegistry() {
        this.defaultExcludePatterns.add("/actuator/health/**");
        this.defaultExcludePatterns.add("/v2/api-docs/**");
        this.defaultExcludePatterns.add("/v2/api-docs-ext/**");
        this.defaultExcludePatterns.add("/auth/**");
        this.defaultExcludePatterns.add("/token/**");
        this.defaultExcludePatterns.add("/code/**");
        this.defaultExcludePatterns.add("/log/**");
        this.defaultExcludePatterns.add("/user/userInfoById");
        this.defaultExcludePatterns.add("/user/userInfo");
        this.defaultExcludePatterns.add("/error/**");
        this.defaultExcludePatterns.add("/assets/**");
    }

    /**
     * 设置放行api
     */
    public SecureRegistry excludePathPatterns(String... patterns) {
        return excludePathPatterns(Arrays.asList(patterns));
    }

    /**
     * 设置放行api
     */
    public SecureRegistry excludePathPatterns(List<String> patterns) {
        this.excludePatterns.addAll(patterns);
        return this;
    }

    /**
     * 批量添加 授权规则
     */
    public SecureRegistry addAuthPatterns(List<AuthSecure> authSecures) {
        this.authSecures.addAll(authSecures);
        return this;
    }
}
