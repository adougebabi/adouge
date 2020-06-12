package com.adouge.service.user.feign;

import com.adouge.core.tool.api.Result;
import com.adouge.service.user.entity.UserInfo;
import com.adouge.service.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Vinson
 * @date : 2020/5/16 3:34 下午
 */
@RequiredArgsConstructor
@RestController
public class UserClient implements IUserClient {
    private final IUserService userService;
    @Override
    public String userInfo() {
        return "{\"data\":{\"userInfo\":{\"username\":\"admin\",\"name\":\"avue\",\"avatar\":\"https://gitee.com/uploads/61/632261_smallweigit.jpg\"},\"roles\":\"admin\",\"permission\":[\"sys_crud_btn_add\",\"sys_crud_btn_export\",\"sys_menu_btn_add\",\"sys_menu_btn_edit\",\"sys_menu_btn_del\",\"sys_role_btn1\",\"sys_role_btn2\",\"sys_role_btn3\",\"sys_role_btn4\",\"sys_role_btn5\",\"sys_role_btn6\"]}}";
    }

    @Override
    public String getTopMenu() {
        return "[{\"label\":\"首页\",\"path\":\"/wel/index\",\"icon\":\"el-icon-document\",\"meta\":{\"i18n\":\"dashboard\"},\"parentId\":0},{\"label\":\"更多\",\"icon\":\"el-icon-document\",\"path\":\"/wel/dashboard\",\"meta\":{\"menu\":false,\"i18n\":\"more\"},\"parentId\":3}]";
    }

    @Override
    public Result<UserInfo> userInfo(String tenantId, String account, String password) {
        return Result.data(userService.userInfo(tenantId, account, password));
    }
}
