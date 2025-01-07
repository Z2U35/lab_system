package jmu.lx.lab.controller;

import jmu.lx.lab.common.Result;
import jmu.lx.lab.entity.Borrow;
import jmu.lx.lab.entity.Lab;
import jmu.lx.lab.entity.Schedule;
import jmu.lx.lab.service.ScheduleService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 排课室表前端操作接口
 **/
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Resource
    private ScheduleService scheduleService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Schedule schedule) {
        scheduleService.add(schedule);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        scheduleService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        scheduleService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Schedule schedule) {
        scheduleService.updateById(schedule);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Schedule schedule = scheduleService.selectById(id);
        return Result.success(schedule);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Schedule schedule ) {
        List<Schedule> list = scheduleService.selectAll(schedule);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Schedule schedule,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Schedule> page = scheduleService.selectPage(schedule, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * 查询是否已排课
     */
    @GetMapping("/isScheduled")
    public Result isScheduled(@RequestBody Borrow borrow) {
        int count = scheduleService.isScheduled(borrow);
        return Result.success(count);
    }

    /**
     * 找到可以排课的实验室
     */
    @PostMapping("/selectFreeLabs")
    public Result selectFreeLabs(@RequestBody Schedule schedule) {
        List<Lab> list = scheduleService.selectFreeLabs(schedule);
        return Result.success(list);
    }

    /**
     * 排课
     */
    @PutMapping("/doScheduled")
    public Result doScheduled(@RequestBody Schedule schedule) {
        scheduleService.doScheduled(schedule);
        return Result.success();
    }

    /**
     * 查询某天某个实验室的课程安排（已排课）
     */
    @PostMapping("/findCourse")
    public Result findCourse(@RequestBody Schedule schedule) {
        Schedule course = scheduleService.findCourse(schedule);
        return Result.success(course);
    }

}