package jmu.lx.lab.mapper;

import jmu.lx.lab.entity.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作Student相关数据接口
*/
public interface StudentMapper {

    /**
      * 新增
    */
    int insert(Student student);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Student student);

    /**
      * 根据ID查询
    */
    Student selectById(Integer id);

    /**
      * 查询所有
    */
    List<Student> selectAll(Student student);

    @Select("select * from student where username = #{username}")
    Student selectByUsername(String username);

    /**
     * 重置密码
     */
    int resetPassword(Integer id);
}