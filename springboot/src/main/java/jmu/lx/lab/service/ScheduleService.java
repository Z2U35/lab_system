package jmu.lx.lab.service;

import jmu.lx.lab.common.enums.RoleEnum;
import jmu.lx.lab.entity.Account;
import jmu.lx.lab.entity.Borrow;
import jmu.lx.lab.entity.Lab;
import jmu.lx.lab.entity.Schedule;
import jmu.lx.lab.mapper.ScheduleMapper;
import jmu.lx.lab.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 排课室表业务处理
 **/
@Service
public class ScheduleService {

    @Resource
    private ScheduleMapper scheduleMapper;

    /**
     * 新增
     */
    public void add(Schedule schedule) {
        scheduleMapper.insert(schedule);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        scheduleMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            scheduleMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Schedule schedule) {
        scheduleMapper.updateById(schedule);
    }

    /**
     * 根据ID查询
     */
    public Schedule selectById(Integer id) {
        return scheduleMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Schedule> selectAll(Schedule schedule) {
        return scheduleMapper.selectAll(schedule);
    }

    /**
     * 分页查询
     */
    public PageInfo<Schedule> selectPage(Schedule schedule, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.TEACHER.name().equals(currentUser.getRole())) {
            schedule.setTeacherId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Schedule> list = scheduleMapper.selectAll(schedule);
        return PageInfo.of(list);
    }

    /**
     * 查询是否已排课
     */
    public int isScheduled(Borrow borrow) {
        return scheduleMapper.isScheduled(borrow);
    }

    /**
     * 找到可以排课的实验室
     */
    public List<Lab> selectFreeLabs(Schedule schedule) {
        return scheduleMapper.selectFreeLabs(schedule);
    }

    /**
     * 排课
     */
    public void doScheduled(Schedule schedule) {
        scheduleMapper.doScheduled(schedule);
    }

    /**
     * 查询某天某个实验室的课程安排（已排课）
     */
    public Schedule findCourse(Schedule schedule) {
        return scheduleMapper.findCourse(schedule);
    }

}