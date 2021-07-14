package fun.snomis.motion.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * swagger3 配置参数
 *
 * @author fibreyu
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {
    private String title;
    private Integer port;
    private String version;
    private Boolean enable;
    private String description;
}
