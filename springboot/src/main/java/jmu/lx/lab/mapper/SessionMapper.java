package jmu.lx.lab.mapper;

import jmu.lx.lab.entity.Session;

import java.util.List;

/**
 * 操作 session 表相关数据的 Mapper 接口
 * 提供对 Session 实体类的增、删、改、查等数据库操作方法。
 */
public interface SessionMapper {

    /**
     * 新增操作：向 session 表中插入一条新的 session 记录
     *
     * @param session 要插入的 Session 对象
     * @return 插入成功的记录数（通常为1）
     */
    int insert(Session session);

    /**
     * 删除操作：根据给定的 ID 删除 session 表中的 session 记录
     *
     * @param id 要删除的 Session 对象的 ID
     * @return 删除成功的记录数（通常为1）
     */
    int deleteById(Integer id);

    /**
     * 修改操作：根据给定的 Session 对象更新 session 表中的 session 记录
     *
     * @param session 包含更新数据的 Session 对象
     * @return 更新成功的记录数（通常为1）
     */
    int updateById(Session session);

    /**
     * 查询操作：根据给定的 ID 查询 session 表中的 session 记录
     *
     * @param id 要查询的 Session 对象的 ID
     * @return 对应 ID 的 Session 对象，如果不存在则返回 null
     */
    Session selectById(Integer id);

    /**
     * 查询所有操作：查询 session 表中的所有 session 记录
     *
     * @param session 可能包含查询条件的 Session 对象（如果查询条件为空，可以传入一个空对象）
     * @return 所有符合条件的 Session 对象列表
     */
    List<Session> selectAll(Session session);
}
