package com.adouge.core.datascope.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/8/13 10:08 下午
 */
@ConfigurationProperties(prefix = "adouge.data-scope")
@Data
public class DataScopeProperties {
    private Boolean enabled = Boolean.TRUE;
    private List<String> mapperKey = Arrays.asList("page", "Page", "list", "List");
    private List<String> mapperExclude = Arrays.asList("UserRoleMapper","UserDeptMapper");
}
