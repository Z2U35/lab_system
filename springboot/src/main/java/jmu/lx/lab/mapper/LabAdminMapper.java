package jmu.lx.lab.mapper;

import jmu.lx.lab.entity.LabAdmin;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作 LabAdmin 表相关数据的 Mapper 接口
 * 提供对 LabAdmin 实体类的增、删、改、查等数据库操作方法。
 */
public interface LabAdminMapper {

    /**
     * 新增操作：向 lab_admin 表中插入一条新的实验室管理员记录
     *
     * @param labAdmin 要插入的 LabAdmin 对象
     * @return 插入成功的记录数（通常为1）
     */
    int insert(LabAdmin labAdmin);

    /**
     * 删除操作：根据给定的 ID 删除 lab_admin 表中的管理员记录
     *
     * @param id 要删除的 LabAdmin 对象的 ID
     * @return 删除成功的记录数（通常为1）
     */
    int deleteById(Integer id);

    /**
     * 修改操作：根据给定的 LabAdmin 对象更新 lab_admin 表中的管理员记录
     *
     * @param labAdmin 包含更新数据的 LabAdmin 对象
     * @return 更新成功的记录数（通常为1）
     */
    int updateById(LabAdmin labAdmin);

    /**
     * 查询操作：根据给定的 ID 查询 lab_admin 表中的管理员记录
     *
     * @param id 要查询的 LabAdmin 对象的 ID
     * @return 对应 ID 的 LabAdmin 对象，如果不存在则返回 null
     */
    LabAdmin selectById(Integer id);

    /**
     * 查询所有操作：查询 lab_admin 表中的所有管理员记录
     *
     * @param labAdmin 可能包含查询条件的 LabAdmin 对象（如果查询条件为空，可以传入一个空对象）
     * @return 所有符合条件的 LabAdmin 对象列表
     */
    List<LabAdmin> selectAll(LabAdmin labAdmin);

    /**
     * 根据用户名查询操作：通过用户名查询对应的实验室管理员记录
     *
     * @param username 要查询的用户名
     * @return 对应用户名的 LabAdmin 对象，如果不存在则返回 null
     */
    @Select("select * from lab_admin where username = #{username}")
    LabAdmin selectByUsername(String username);

    /**
     * 重置密码操作：根据管理员的 ID 重置其密码
     * 通常用于将密码设置为默认值或随机新密码。
     *
     * @param id 要重置密码的管理员 ID
     * @return 更新成功的记录数（通常为1）
     */
    int resetPassword(Integer id);
}
