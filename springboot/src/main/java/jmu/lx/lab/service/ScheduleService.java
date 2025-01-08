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
 * 排课表业务处理
 * 该类负责处理与排课相关的业务逻辑，包括增删改查、查询空闲实验室、排课等操作。
 */
@Service
public class ScheduleService {

    // 注入 ScheduleMapper，用于访问数据库
    @Resource
    private ScheduleMapper scheduleMapper;

    /**
     * 新增排课记录
     * @param schedule  需要新增的排课实体对象
     */
    public void add(Schedule schedule) {
        // 调用Mapper层的insert方法将排课信息插入数据库
        scheduleMapper.insert(schedule);
    }

    /**
     * 根据ID删除排课记录
     * @param id  要删除的排课记录ID
     */
    public void deleteById(Integer id) {
        // 调用Mapper层的deleteById方法根据ID删除排课记录
        scheduleMapper.deleteById(id);
    }

    /**
     * 批量删除排课记录
     * @param ids  要删除的排课记录ID列表
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            // 循环调用Mapper层的deleteById方法删除多个排课记录
            scheduleMapper.deleteById(id);
        }
    }

    /**
     * 根据ID修改排课记录信息
     * @param schedule 包含要修改的排课信息的实体对象，必须包含ID
     */
    public void updateById(Schedule schedule) {
        // 调用Mapper层的updateById方法更新排课记录信息
        scheduleMapper.updateById(schedule);
    }

    /**
     * 根据ID查询排课记录
     * @param id 要查询的排课记录ID
     * @return 查询到的排课记录实体对象，如果不存在则返回null
     */
    public Schedule selectById(Integer id) {
        // 调用Mapper层的selectById方法根据ID查询排课记录
        return scheduleMapper.selectById(id);
    }

    /**
     * 查询所有排课记录
     * @param schedule 查询条件，可以为空，表示查询所有
     * @return 查询到的排课记录列表
     */
    public List<Schedule> selectAll(Schedule schedule) {
        // 调用Mapper层的selectAll方法查询所有排课记录
        return scheduleMapper.selectAll(schedule);
    }

    /**
     * 分页查询排课记录
     * 根据当前用户角色设置查询条件（教师只能查询自己的排课）
     * @param schedule 查询条件，可以为空，表示查询所有
     * @param pageNum 当前页码，从1开始
     * @param pageSize 每页显示的数量
     * @return 包含分页信息的PageInfo对象
     */
    public PageInfo<Schedule> selectPage(Schedule schedule, Integer pageNum, Integer pageSize) {
        // 获取当前登录用户信息
        Account currentUser = TokenUtils.getCurrentUser();
        // 如果当前用户是教师，则设置查询条件为只查询该教师的排课记录
        if (RoleEnum.TEACHER.name().equals(currentUser.getRole())) {
            schedule.setTeacherId(currentUser.getId());
        }
        // 使用PageHelper设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        // 查询当前页的排课记录数据
        List<Schedule> list = scheduleMapper.selectAll(schedule);
        // 将查询结果封装成PageInfo对象返回
        return PageInfo.of(list);
    }

    /**
     * 查询某个借用记录是否已排课
     * @param borrow  包含借用信息的对象
     * @return  如果已排课，返回大于0的整数，否则返回0
     */
    public int isScheduled(Borrow borrow) {
        // 调用Mapper层的isScheduled方法查询借用记录是否已排课
        return scheduleMapper.isScheduled(borrow);
    }

    /**
     * 找到可以排课的实验室
     * @param schedule 查询条件，包含日期和时间信息
     * @return  符合条件的空闲实验室列表
     */
    public List<Lab> selectFreeLabs(Schedule schedule) {
        // 调用Mapper层的selectFreeLabs方法查询符合条件的空闲实验室
        return scheduleMapper.selectFreeLabs(schedule);
    }


    /**
     *  执行排课操作
     * @param schedule  包含排课信息的对象
     */
    public void doScheduled(Schedule schedule) {
        // 调用Mapper层的doScheduled方法执行排课操作
        scheduleMapper.doScheduled(schedule);
    }

    /**
     * 查询某天某个实验室的课程安排
     * @param schedule 查询条件，包含日期和实验室ID信息
     * @return 查询到的排课记录，如果不存在则返回null
     */
    public Schedule findCourse(Schedule schedule) {
        // 调用Mapper层的findCourse方法查询某天某个实验室的课程安排
        return scheduleMapper.findCourse(schedule);
    }

}