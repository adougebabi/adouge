package com.adouge.secure.provider;

import cn.hutool.json.JSONUtil;
import com.adouge.core.tool.api.Result;
import com.adouge.core.tool.api.ResultCode;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author : Vinson
 * @date : 2020/8/13 3:08 下午
 */
@Slf4j
public class ResponseProvider {
    public static void write(HttpServletResponse response) {
        Result<?> result = Result.fail(ResultCode.UN_AUTHORIZED);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(Objects.requireNonNull(JSONUtil.toJsonStr(result)));
        } catch (IOException var3) {
            log.error(var3.getMessage());
        }

    }
}
