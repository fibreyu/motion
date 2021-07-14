package fun.snomis.motion.service.impl;

import fun.snomis.motion.config.security.JwtTokenUtil;
import fun.snomis.motion.mapper.AdminMapper;
import fun.snomis.motion.pojo.Admin;
import fun.snomis.motion.pojo.ResBean;
import fun.snomis.motion.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录实现类
 *
 * @author fibreyu
 * @since 1.0.0
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    @Override
    public Admin getAdminByUserName(String username) {
        Admin admin = adminMapper.getAdminByUserName(username);
        return admin;
    }

    /**
     * 登录获取token
     *
     * @param username
     * @param password
     * @param code
     * @return
     */
    @Override
    public ResBean login(String username, String password, String code, HttpServletRequest request) {
        /*
        String captcha = (String) request.getSession().getAttribute("captcha");
        if (StringUtils.isEmpty(code) || !captcha.equalsIgnoreCase(code)) {
            return ResBean.error("验证码输入错误, 请重新输入");
        }
        */

        // 登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (null == userDetails || !passwordEncoder.matches(password,userDetails.getPassword())) {
            return ResBean.error("用户名或密码不正确");
        }

        if (!userDetails.isEnabled()) {
            return ResBean.error("账号被禁用，请联系管理员");
        }

        // 更新security登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead",tokenHead);
        return ResBean.success("登录成功", tokenMap);
    }
}
