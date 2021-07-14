package fun.snomis.motion;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * security测试
 *
 * @author fibreyu
 * @since 1.0.0
 */
@SpringBootTest
public class SecurityTest {

    @Test
    public void testPw() {
        PasswordEncoder pw = new BCryptPasswordEncoder();
        String encode = pw.encode("123456");
        System.out.println(encode);
        boolean matches = pw.matches("123456", encode);
        System.out.println(matches);
    }
}
