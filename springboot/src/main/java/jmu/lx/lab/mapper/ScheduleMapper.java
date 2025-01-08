package jmu.lx.lab.mapper;

import jmu.lx.lab.entity.Borrow;
import jmu.lx.lab.entity.Lab;
import jmu.lx.lab.entity.Schedule;

import java.util.List;

/**
 * 操作 schedule 表相关数据的 Mapper 接口
 * 提供对 Schedule 实体类的增、删、改、查以及排课相关操作方法。
 */
public interface ScheduleMapper {

    /**
     * 新增操作：向 schedule 表中插入一条新的课程安排记录
     *
     * @param schedule 要插入的 Schedule 对象
     * @return 插入成功的记录数（通常为1）
     */
    int insert(Schedule schedule);

    /**
     * 删除操作：根据给定的 ID 删除 schedule 表中的课程安排记录
     *
     * @param id 要删除的 Schedule 对象的 ID
     * @return 删除成功的记录数（通常为1）
     */
    int deleteById(Integer id);

    /**
     * 修改操作：根据给定的 Schedule 对象更新 schedule 表中的课程安排记录
     *
     * @param schedule 包含更新数据的 Schedule 对象
     * @return 更新成功的记录数（通常为1）
     */
    int updateById(Schedule schedule);

    /**
     * 查询操作：根据给定的 ID 查询 schedule 表中的课程安排记录
     *
     * @param id 要查询的 Schedule 对象的 ID
     * @return 对应 ID 的 Schedule 对象，如果不存在则返回 null
     */
    Schedule selectById(Integer id);

    /**
     * 查询所有操作：查询 schedule 表中的所有课程安排记录
     *
     * @param schedule 可能包含查询条件的 Schedule 对象（如果查询条件为空，可以传入一个空对象）
     * @return 所有符合条件的 Schedule 对象列表
     */
    List<Schedule> selectAll(Schedule schedule);

    /**
     * 查询是否已排课：根据 Borrow 对象检查是否存在与其冲突的排课记录
     *
     * @param borrow 包含借用信息的 Borrow 对象
     * @return 是否已排课（1 表示已排课，0 表示未排课）
     */
    int isScheduled(Borrow borrow);

    /**
     * 找到可以排课的实验室：根据 Schedule 对象的条件筛选未被占用的实验室
     *
     * @param schedule 包含筛选条件的 Schedule 对象
     * @return 所有符合条件的未被占用的实验室列表
     */
    List<Lab> selectFreeLabs(Schedule schedule);

    /**
     * 排课操作：为给定的 Schedule 对象添加排课记录
     *
     * @param schedule 包含排课信息的 Schedule 对象
     * @return 排课成功的记录数（通常为1）
     */
    int doScheduled(Schedule schedule);

    /**
     * 查询某天某个实验室的课程安排：根据 Schedule 对象的日期和实验室信息查询已排课记录
     *
     * @param schedule 包含日期和实验室信息的 Schedule 对象
     * @return 符合条件的 Schedule 对象，如果不存在则返回 null
     */
    Schedule findCourse(Schedule schedule);
}
