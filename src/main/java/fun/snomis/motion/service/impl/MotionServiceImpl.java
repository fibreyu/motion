package fun.snomis.motion.service.impl;

import fun.snomis.motion.mapper.MotionMapper;
import fun.snomis.motion.pojo.Motion;
import fun.snomis.motion.pojo.QueryBean;
import fun.snomis.motion.service.MotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * 服务实现类
 *
 * @author fibreyu
 * @since 1.0.0
 */
@Service
public class MotionServiceImpl implements MotionService {

    @Autowired
    private MotionMapper motionMapper;

    /**
     * 根据id获取心情
     *
     * @param id
     */
    @Override
    public List<Motion> getMotionsById(Integer id) {
        return motionMapper.getMotionsById(id);

    }

    /**
     * 添加心情
     *
     * @param motion
     * @return
     */
    @Override
    public Integer addMotion(Motion motion) {
        if (motion.getRemark() == null) {
            motion.setRemark("");
        }
        if (motion.getTime() == null) {
            motion.setTime(new Date());
        }
        Integer result = motionMapper.addMotion(motion);
        return result;
    }

    /**
     * 更改心情
     *
     * @param motion
     * @return
     */
    @Override
    public Integer updateMotion(Motion motion) {
        return motionMapper.updateMotion(motion);
    }

    /**
     * 根据时间获取心情
     *
     * @param queryBean
     * @return
     */
    @Override
    public List<Motion> getMotionsByDate(QueryBean queryBean) {
        if (queryBean.getEnd() == null) {
            queryBean.setEnd(new Date());
        }

        if (queryBean.getStart() == null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                queryBean.setStart(sdf.parse("2021-07-01"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return motionMapper.getMotionsByDate(queryBean);
    }
}
