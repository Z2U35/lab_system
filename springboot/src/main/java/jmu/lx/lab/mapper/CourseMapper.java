package jmu.lx.lab.mapper;

import jmu.lx.lab.entity.Course;

import java.util.List;

/**
 * 操作course相关数据接口
*/
public interface CourseMapper {

    /**
      * 新增
    */
    int insert(Course course);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Course course);

    /**
      * 根据ID查询
    */
    Course selectById(Integer id);

    /**
      * 查询所有
    */
    List<Course> selectAll(Course course);

    /**
     * 通过申请
     */
    int pass(Course course);

    /**
     * 驳回申请
     */
    int reCourse(Course course);

    /**
     * 确认申请完成
     */
    int finish(Course course);
}