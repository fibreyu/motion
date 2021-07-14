package fun.snomis.motion.pojo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回类
 *
 * @author fibreyu
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "返回对象")
public class ResBean {
    private long code;
    private String message;
    private Object obj;

    /**
     * 成功返回结果
     * @param message
     * @return
     */
    public static ResBean success(String message) {
        return new ResBean(200, message, null);
    }

    /**
     * 成功返回结果
     * @param message
     * @return
     */
    public static ResBean success(String message, Object obj) {
        return new ResBean(200, message, obj);
    }

    /**
     * 失败返回结果
     * @param message
     * @return
     */
    public static ResBean error(String message) {
        return new ResBean(500, message, null);
    }

    /**
     * 失败返回结果
     * @param message
     * @return
     */
    public static ResBean error(String message, Object obj) {
        return new ResBean(500, message, obj);
    }
}
