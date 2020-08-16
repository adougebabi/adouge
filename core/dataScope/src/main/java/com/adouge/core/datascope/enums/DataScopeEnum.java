package com.adouge.core.datascope.enums;

import java.util.Arrays;

/**
 * @author : Vinson
 * @date : 2020/8/13 9:57 下午
 */
public enum DataScopeEnum {
    ALL(1, "全部"),
    OWN(2, "本人可见"),
    OWN_DEPT(3, "所在机构可见"),
    OWN_DEPT_CHILD(4, "所在机构及子级可见"),
    CUSTOM(5, "自定义");

    private final int type;
    private final String description;

    public static DataScopeEnum of(Integer dataScopeType) {
        if (dataScopeType == null) {
            return null;
        } else {
            return Arrays.stream(values()).filter(s -> s.getType() == dataScopeType).findFirst().orElse(null);
        }
    }

    public int getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }

    DataScopeEnum(final int type, final String description) {
        this.type = type;
        this.description = description;
    }
}

