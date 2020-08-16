package com.adouge.auth.controller;

import cn.hutool.core.util.ObjectUtil;
import com.adouge.auth.constant.AuthConstant;
import com.adouge.auth.granter.ITokenGranter;
import com.adouge.auth.granter.TokenBuilder;
import com.adouge.auth.granter.TokenParameter;
import com.adouge.auth.utils.TokenUtils;
import com.adouge.core.tool.api.Result;
import com.adouge.core.tool.api.ResultCode;
import com.adouge.core.tool.utils.RedisUtil;
import com.adouge.core.tool.utils.WebUtil;
import com.adouge.secure.AdougeUser;
import com.adouge.service.user.entity.UserInfo;
import com.wf.captcha.GifCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author : Vinson
 * @date : 2020/5/25 10:54 下午
 */
@RestController
@Api(value = "用户授权认证", tags = "授权接口")
@RequiredArgsConstructor
public class AuthController {

    private final RedisUtil redisUtil;

    @GetMapping("token")
    @ApiOperation(value = "获取认证token", notes = "传入租户ID:tenantId,账号:account,密码:password")
    public Result<?> auth(
            @ApiParam(value = "授权类型", required = true) @RequestParam(defaultValue = "password", required = false) String grantType,
            @ApiParam(value = "刷新令牌") @RequestParam(required = false) String refreshToken,
            @ApiParam(value = "租户ID", required = true) @RequestParam(defaultValue = "000000", required = false) String tenantId,
            @ApiParam(value = "账号") @RequestParam(required = false) String account,
            @ApiParam(value = "密码") @RequestParam(required = false) String password) {
        TokenParameter tokenParameter = new TokenParameter();
        tokenParameter.getArgs().set("tenantId", tenantId).set("account", account)
                .set("password", password)
                .set("grantType", grantType)
                .set("refreshToken", refreshToken)
                .set("userType", AuthConstant.DEFAULT_USER_TYPE);

        ITokenGranter granter = TokenBuilder.getGranter(grantType);
        assert granter != null;
        UserInfo userInfo = granter.grant(tokenParameter);
        if (ObjectUtil.isEmpty(userInfo) || ObjectUtil.isEmpty(userInfo.getUser())) {
            WebUtil.setStatus(ResultCode.FAILURE.getCode());
            return Result.fail(AuthConstant.USER_NOT_FOUND);
        }
        return Result.data(TokenUtils.createAuthInfo(userInfo));
    }

    @GetMapping("code/{str}")
    @ApiOperation(value = "获取验证码")
    @SneakyThrows
    public void code(HttpServletResponse response, @PathVariable String str) {
        // 设置请求头为输出图片类型
        GifCaptcha gifCaptcha = new GifCaptcha(130, 48, 5);
        String code = gifCaptcha.text().toLowerCase();
        redisUtil.set(str, code, 1000 * 30);
        // 将key和base64返回给前端
        gifCaptcha.out(response.getOutputStream());
    }

    @GetMapping("logout")
    public Result<?> logout(){
        return Result.success("");
    }
    @PostMapping("refreshToken")
    public Result<?> refreshToken(AdougeUser user){
//        ITokenGranter granter = TokenBuilder.getGranter(grantType);
        return Result.success("");
    }
}
