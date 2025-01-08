package jmu.lx.lab.mapper;

import jmu.lx.lab.entity.Teacher;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作 teacher 表相关数据的 Mapper 接口
 * 提供对 Teacher 实体类的增、删、改、查等数据库操作方法。
 */
public interface TeacherMapper {

    /**
     * 新增操作：向 teacher 表中插入一条新的教师记录
     *
     * @param teacher 要插入的 Teacher 对象
     * @return 插入成功的记录数（通常为1）
     */
    int insert(Teacher teacher);

    /**
     * 删除操作：根据给定的 ID 删除 teacher 表中的教师记录
     *
     * @param id 要删除的 Teacher 对象的 ID
     * @return 删除成功的记录数（通常为1）
     */
    int deleteById(Integer id);

    /**
     * 修改操作：根据给定的 Teacher 对象更新 teacher 表中的教师记录
     *
     * @param teacher 包含更新数据的 Teacher 对象
     * @return 更新成功的记录数（通常为1）
     */
    int updateById(Teacher teacher);

    /**
     * 查询操作：根据给定的 ID 查询 teacher 表中的教师记录
     *
     * @param id 要查询的 Teacher 对象的 ID
     * @return 对应 ID 的 Teacher 对象，如果不存在则返回 null
     */
    Teacher selectById(Integer id);

    /**
     * 查询所有操作：查询 teacher 表中的所有教师记录
     *
     * @param teacher 可能包含查询条件的 Teacher 对象（如果查询条件为空，可以传入一个空对象）
     * @return 所有符合条件的 Teacher 对象列表
     */
    List<Teacher> selectAll(Teacher teacher);

    /**
     * 根据用户名查询教师：根据给定的用户名查询 teacher 表中的教师记录
     *
     * @param username 要查询的教师的用户名
     * @return 对应用户名的 Teacher 对象，如果不存在则返回 null
     */
    @Select("select * from teacher where username = #{username}")
    Teacher selectByUsername(String username);

    /**
     * 重置密码操作：根据给定的教师 ID 重置密码
     *
     * @param id 要重置密码的教师的 ID
     * @return 更新成功的记录数（通常为1）
     */
    int resetPassword(Integer id);
}
