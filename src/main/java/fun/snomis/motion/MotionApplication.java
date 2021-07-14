package fun.snomis.motion;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("fun.snomis.motion.mapper")
public class MotionApplication {

    public static void main(String[] args) {
        SpringApplication.run(MotionApplication.class, args);
    }

}
