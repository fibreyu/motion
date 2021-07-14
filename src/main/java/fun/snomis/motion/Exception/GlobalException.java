package fun.snomis.motion.Exception;

import fun.snomis.motion.pojo.ResBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;

/**
 * 全局一场处理
 *
 * @author fibreyu
 * @since 1.0.0
 */

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(SQLException.class)
    public ResBean mySqlException(SQLException e) {
        if (e instanceof SQLIntegrityConstraintViolationException) {
            return ResBean.error("该数据有关联数据，操作失败！");
        }
        return ResBean.error("数据库异常，操作失败!");
    }

    @ExceptionHandler(ParseException.class)
    public ResBean myDateException(ParseException e) {
        return ResBean.error("日期解析异常!");
    }
}
