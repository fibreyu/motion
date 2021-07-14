package fun.snomis.motion.mapper;

import fun.snomis.motion.pojo.Motion;
import fun.snomis.motion.pojo.QueryBean;

import java.util.List;

public interface MotionMapper {
    /**
     * 根据id获取心情
     *
     * @param id
     */
    List<Motion> getMotionsById(Integer id);

    /**
     * 添加心情
     *
     * @param motion
     * @return
     */
    Integer addMotion(Motion motion);

    /**
     * 更改心情
     *
     * @param motion
     * @return
     */
    Integer updateMotion(Motion motion);

    /**
     * 根据时间获取心情
     *
     * @param queryBean
     * @return
     */
    List<Motion> getMotionsByDate(QueryBean queryBean);
}
