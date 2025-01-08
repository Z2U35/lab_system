package jmu.lx.lab.mapper;

import jmu.lx.lab.entity.Course;

import java.util.List;

/**
 * 操作 course 表相关数据的 Mapper 接口
 * 提供对 Course 实体类的增、删、改、查等数据库操作方法。
 */
public interface CourseMapper {

    /**
     * 新增操作：向 course 表中插入一条新的课程记录
     *
     * @param course 要插入的 Course 对象
     * @return 插入成功的记录数（通常为1）
     */
    int insert(Course course);

    /**
     * 删除操作：根据给定的 ID 删除 course 表中的课程记录
     *
     * @param id 要删除的 Course 对象的 ID
     * @return 删除成功的记录数（通常为1）
     */
    int deleteById(Integer id);

    /**
     * 修改操作：根据给定的 Course 对象更新 course 表中的课程记录
     *
     * @param course 包含更新数据的 Course 对象
     * @return 更新成功的记录数（通常为1）
     */
    int updateById(Course course);

    /**
     * 查询操作：根据给定的 ID 查询 course 表中的课程记录
     *
     * @param id 要查询的 Course 对象的 ID
     * @return 对应 ID 的 Course 对象，如果不存在则返回 null
     */
    Course selectById(Integer id);

    /**
     * 查询所有操作：查询 course 表中的所有课程记录
     *
     * @param course 可能包含查询条件的 Course 对象（如果查询条件为空，可以传入一个空对象）
     * @return 所有符合条件的 Course 对象列表
     */
    List<Course> selectAll(Course course);

    /**
     * 通过申请：批准课程申请，将课程状态设置为通过
     *
     * @param course 包含课程申请信息的 Course 对象
     * @return 更新成功的记录数（通常为1）
     */
    int pass(Course course);

    /**
     * 驳回申请：驳回课程申请，将课程状态设置为未通过
     *
     * @param course 包含课程申请信息的 Course 对象
     * @return 更新成功的记录数（通常为1）
     */
    int reCourse(Course course);

    /**
     * 确认申请完成：确认课程申请已完成，更新课程状态
     *
     * @param course 包含课程申请信息的 Course 对象
     * @return 更新成功的记录数（通常为1）
     */
    int finish(Course course);
}
