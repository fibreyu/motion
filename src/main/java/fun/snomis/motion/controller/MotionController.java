package fun.snomis.motion.controller;

import fun.snomis.motion.pojo.Level;
import fun.snomis.motion.pojo.Motion;
import fun.snomis.motion.pojo.QueryBean;
import fun.snomis.motion.pojo.ResBean;
import fun.snomis.motion.service.MotionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前端控制类
 *
 * @author fibreyu
 * @since 1.0.0
 */
@Api(tags = "MotionController")
@RestController
@RequestMapping("/")
public class MotionController {

    @Autowired
    private MotionService motionService;

    /**
     * 根据id获取心情
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id获取心情")
    @GetMapping("/get/{id}")
    public ResBean getMotionsById(@PathVariable("id") Integer id) {
        List<Motion> list = motionService.getMotionsById(id);
        if (list == null || list.size() == 0) {
            return ResBean.error("请求失败，未获取参数！");
        } else {
            return ResBean.success("请求成功！", list);
        }
    }

    /**
     * 获取所有心情
     *
     * @return
     */
    @ApiOperation(value = "获取所有心情")
    @GetMapping("/get/all")
    public ResBean getAllMotions() {
        List<Motion> list = motionService.getMotionsById(null);
        if (list == null || list.size() == 0) {
            return ResBean.error("请求失败，未获取参数！");
        } else {
            return ResBean.success("请求成功！", list);
        }
    }

    /**
     * 根据时间获取心情
     *
     * @param queryBean
     * @return
     */
    @ApiOperation(value = "根据日期获取心情")
    @PostMapping("/get/date")
    public ResBean getMotionsByDate(@RequestBody QueryBean queryBean) {

        List<Motion> list = motionService.getMotionsByDate(queryBean);

        System.out.println(list);

        if (list == null) {
            return ResBean.error("数据获取失败！");
        }
        return ResBean.success("数据获取成功！", list);
    }

    /**
     * 添加心情
     *
     * @param motion
     * @return
     */
    @ApiOperation(value = "添加心情")
    @PostMapping("/add")
    public ResBean addMotion(@RequestBody Motion motion) {

        if (!Level.checkIndex(motion.getLevel())) {
            return ResBean.error("参数错误，不合法的心情等级！");
        }

        if (motion.getId() != null) {
            return ResBean.error("数据已存在，插入失败！");
        }
        if (motion.getLevel() == null) {
            return ResBean.error("添加失败，必须传入情感信息！");
        }
        Integer result = motionService.addMotion(motion);
        if (result == null || result == 1) {
            return ResBean.error("新增失败！");
        }
        return ResBean.success("新增成功! ");
    }

    /**
     * 更改心情
     *
     * @param motion
     * @return
     */
    @ApiOperation(value = "更改心情")
    @PostMapping("/update")
    public ResBean updateMotion(@RequestBody Motion motion) {
        if (motion.getId() == null) {
            return ResBean.error("参数错误，未传入id！");
        }

        if (!Level.checkIndex(motion.getLevel())) {
            return ResBean.error("参数错误，不合法的心情等级！");
        }

        Integer result = motionService.updateMotion(motion);
        if (result == null || result == 0) {
            return ResBean.error("数据库错误，更改失败！");
        }

        return ResBean.success("更改成功！");
    }


}
