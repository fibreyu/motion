package fun.snomis.motion.controller;

import fun.snomis.motion.pojo.Admin;
import fun.snomis.motion.pojo.AdminLoginParam;
import fun.snomis.motion.pojo.ResBean;
import fun.snomis.motion.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录控制器
 *
 * @author fibreyu
 * @since 1.0.0
 */
@Api(tags = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "登录获取token")
    @PostMapping("/login")
    public ResBean Login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request) {
        return adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),adminLoginParam.getCode(), request);
    }

}
