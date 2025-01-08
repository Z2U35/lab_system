package jmu.lx.lab.mapper;

import jmu.lx.lab.entity.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作 student 表相关数据的 Mapper 接口
 * 提供对 Student 实体类的增、删、改、查等数据库操作方法。
 */
public interface StudentMapper {

    /**
     * 新增操作：向 student 表中插入一条新的学生记录
     *
     * @param student 要插入的 Student 对象
     * @return 插入成功的记录数（通常为1）
     */
    int insert(Student student);

    /**
     * 删除操作：根据给定的 ID 删除 student 表中的学生记录
     *
     * @param id 要删除的 Student 对象的 ID
     * @return 删除成功的记录数（通常为1）
     */
    int deleteById(Integer id);

    /**
     * 修改操作：根据给定的 Student 对象更新 student 表中的学生记录
     *
     * @param student 包含更新数据的 Student 对象
     * @return 更新成功的记录数（通常为1）
     */
    int updateById(Student student);

    /**
     * 查询操作：根据给定的 ID 查询 student 表中的学生记录
     *
     * @param id 要查询的 Student 对象的 ID
     * @return 对应 ID 的 Student 对象，如果不存在则返回 null
     */
    Student selectById(Integer id);

    /**
     * 查询所有操作：查询 student 表中的所有学生记录
     *
     * @param student 可能包含查询条件的 Student 对象（如果查询条件为空，可以传入一个空对象）
     * @return 所有符合条件的 Student 对象列表
     */
    List<Student> selectAll(Student student);

    /**
     * 根据用户名查询学生：根据给定的用户名查询 student 表中的学生记录
     *
     * @param username 要查询的学生的用户名
     * @return 对应用户名的 Student 对象，如果不存在则返回 null
     */
    @Select("select * from student where username = #{username}")
    Student selectByUsername(String username);

    /**
     * 重置密码操作：根据给定的学生 ID 重置密码
     *
     * @param id 要重置密码的学生的 ID
     * @return 更新成功的记录数（通常为1）
     */
    int resetPassword(Integer id);
}
