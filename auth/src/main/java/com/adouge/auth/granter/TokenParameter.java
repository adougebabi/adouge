package com.adouge.auth.granter;

import com.adouge.core.tool.support.ParamMap;
import lombok.Data;

/**
 * @author : Vinson
 * @date : 2020/5/26 8:34 下午
 */
@Data
public class TokenParameter {
    private ParamMap args=ParamMap.init();
}
