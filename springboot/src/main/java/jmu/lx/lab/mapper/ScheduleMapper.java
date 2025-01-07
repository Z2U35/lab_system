package jmu.lx.lab.mapper;

import jmu.lx.lab.entity.Borrow;
import jmu.lx.lab.entity.Lab;
import jmu.lx.lab.entity.Schedule;

import java.util.List;

/**
 * 操作schedule相关数据接口
*/
public interface ScheduleMapper {

    /**
      * 新增
    */
    int insert(Schedule schedule);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Schedule schedule);

    /**
      * 根据ID查询
    */
    Schedule selectById(Integer id);

    /**
      * 查询所有
    */
    List<Schedule> selectAll(Schedule schedule);

    /**
     * 查询是否已排课
     */
    int isScheduled(Borrow borrow);

    /**
     * 找到可以排课的实验室
     */
    List<Lab> selectFreeLabs(Schedule schedule);

    /**
     * 排课
     */
    int doScheduled(Schedule schedule);

    /**
     * 查询某天某个实验室的课程安排（已排课）
     */
    Schedule findCourse(Schedule schedule);

}