package fun.snomis.motion.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 数据类
 *
 * @author fibreyu
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "motion object")
public class Motion {
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "motion等级")
    private Integer level;
    @ApiModelProperty(value = "时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Shanghai")
    private Date time;
    @ApiModelProperty(value = "备注")
    String remark;

}
