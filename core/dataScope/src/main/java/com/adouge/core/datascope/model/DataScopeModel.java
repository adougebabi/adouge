package com.adouge.core.datascope.model;

import com.adouge.core.datascope.constant.DataScopeConstant;
import com.adouge.core.datascope.enums.DataScopeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : Vinson
 * @date : 2020/8/13 10:05 下午
 */
@Data
public class DataScopeModel implements Serializable {
    private String resourceCode;
    private String scopeColumn = DataScopeConstant.DEFAULT_COLUMN;
    private Integer scopeType;
    private String scopeField;
    private String scopeValue;
    public DataScopeModel() {
        this.scopeType = DataScopeEnum.ALL.getType();
    }

}
