package fun.snomis.motion.service;

import fun.snomis.motion.pojo.Admin;
import fun.snomis.motion.pojo.ResBean;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {


    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    Admin getAdminByUserName(String username);

    /**
     * 登录获取token
     *
     * @param username
     * @param password
     * @param code
     * @return
     */
    ResBean login(String username, String password, String code, HttpServletRequest request);
}
