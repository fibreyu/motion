package fun.snomis.motion.mapper;

import fun.snomis.motion.pojo.Admin;

public interface AdminMapper {

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    Admin getAdminByUserName(String username);
}
