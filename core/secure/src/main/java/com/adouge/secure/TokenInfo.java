package com.adouge.secure;

import lombok.Data;

/**
 * @author : Vinson
 * @date : 2020/5/31 2:21 下午
 */
@Data
public class TokenInfo {
    /**
     * 令牌值
     */
    private String token;

    /**
     * 过期秒数
     */
    private int expire;
}
