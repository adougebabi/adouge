package com.adouge.secure.props;


import com.adouge.secure.provider.HttpMethod;
import lombok.Data;

/**
 * 授权规则
 * @author : Vinson
 * @date : 2020/8/13 11:25 上午
 */
@Data
public class AuthSecure {
    private HttpMethod method;
    private String pattern;
    private String expression;
}
