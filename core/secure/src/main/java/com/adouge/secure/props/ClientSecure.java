package com.adouge.secure.props;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/6/15 1:36 下午
 */
@Data
public class ClientSecure {
    private String clientId;

    private final List<String> pathPatterns = new ArrayList<>();
}
