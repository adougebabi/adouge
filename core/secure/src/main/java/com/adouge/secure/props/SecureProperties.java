package com.adouge.secure.props;

import lombok.Data;
import lombok.RequiredArgsConstructor;
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
    private boolean enabled = false;
    private boolean authEnabled = true;
    private boolean clientEnabled = true;

    private List<String> skipUrl = new ArrayList<>();
    private List<AuthSecure> auth = new ArrayList<>();
    private List<ClientSecure> client = new ArrayList<>();
}
