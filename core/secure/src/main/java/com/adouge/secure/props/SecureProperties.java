package com.adouge.secure.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/6/15 1:37 下午
 */
@Data
@ConfigurationProperties("adouge.secure")
public class SecureProperties {
    private final List<ClientSecure> client = new ArrayList<>();

    private final List<String> skipUrl = new ArrayList<>();
}
