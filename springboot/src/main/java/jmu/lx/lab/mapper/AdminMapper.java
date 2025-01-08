package jmu.lx.lab.mapper;

import jmu.lx.lab.entity.Admin;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作 admin 表相关数据的 Mapper 接口
 * 提供了对 Admin 实体类的增、删、改、查等数据库操作方法。
 */
public interface AdminMapper {

    /**
     * 新增操作：向 admin 表中插入一条新的记录
     *
     * @param admin 要插入的 Admin 对象
     * @return 插入成功的记录数（通常为1）
     */
    int insert(Admin admin);

    /**
     * 删除操作：根据给定的 ID 删除 admin 表中的记录
     *
     * @param id 要删除的 Admin 对象的 ID
     * @return 删除成功的记录数（通常为1）
     */
    int deleteById(Integer id);

    /**
     * 修改操作：根据给定的 Admin 对象更新 admin 表中的记录
     *
     * @param admin 包含更新数据的 Admin 对象
     * @return 更新成功的记录数（通常为1）
     */
    int updateById(Admin admin);

    /**
     * 查询操作：根据给定的 ID 查询 admin 表中的记录
     *
     * @param id 要查询的 Admin 对象的 ID
     * @return 对应 ID 的 Admin 对象，如果不存在则返回 null
     */
    Admin selectById(Integer id);

    /**
     * 查询所有：查询 admin 表中的所有记录
     *
     * @param admin 可能包含查询条件的 Admin 对象（如果查询条件为空，可以传入一个空对象）
     * @return 所有符合条件的 Admin 对象列表
     */
    List<Admin> selectAll(Admin admin);

    /**
     * 根据用户名查询：根据给定的用户名查找 admin 表中的对应记录
     *
     * @param username 要查询的用户名
     * @return 对应用户名的 Admin 对象，如果不存在则返回 null
     */
    @Select("select * from admin where username = #{username}")
    Admin selectByUsername(String username);
}
