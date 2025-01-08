package jmu.lx.lab.mapper;

import jmu.lx.lab.entity.Repair;

import java.util.List;

/**
 * 操作 repair 表相关数据的 Mapper 接口
 * 提供对 Repair 实体类的增、删、改、查等数据库操作方法。
 */
public interface RepairMapper {

    /**
     * 新增操作：向 repair 表中插入一条新的报修记录
     *
     * @param repair 要插入的 Repair 对象
     * @return 插入成功的记录数（通常为1）
     */
    int insert(Repair repair);

    /**
     * 删除操作：根据给定的 ID 删除 repair 表中的报修记录
     *
     * @param id 要删除的 Repair 对象的 ID
     * @return 删除成功的记录数（通常为1）
     */
    int deleteById(Integer id);

    /**
     * 修改操作：根据给定的 Repair 对象更新 repair 表中的报修记录
     *
     * @param repair 包含更新数据的 Repair 对象
     * @return 更新成功的记录数（通常为1）
     */
    int updateById(Repair repair);

    /**
     * 查询操作：根据给定的 ID 查询 repair 表中的报修记录
     *
     * @param id 要查询的 Repair 对象的 ID
     * @return 对应 ID 的 Repair 对象，如果不存在则返回 null
     */
    Repair selectById(Integer id);

    /**
     * 查询所有操作：查询 repair 表中的所有报修记录
     *
     * @param repair 可能包含查询条件的 Repair 对象（如果查询条件为空，可以传入一个空对象）
     * @return 所有符合条件的 Repair 对象列表
     */
    List<Repair> selectAll(Repair repair);

    /**
     * 报修处理中操作：更新报修记录状态为处理中
     *
     * @param repair 包含报修记录信息的 Repair 对象
     * @return 更新成功的记录数（通常为1）
     */
    int repairing(Repair repair);

    /**
     * 报修完成操作：更新报修记录状态为已完成
     *
     * @param repair 包含报修记录信息的 Repair 对象
     * @return 更新成功的记录数（通常为1）
     */
    int repaired(Repair repair);
}
