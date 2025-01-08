package jmu.lx.lab.mapper;

import jmu.lx.lab.entity.Borrow;

import java.util.List;

/**
 * 操作 borrow 表相关数据的 Mapper 接口
 * 提供了对 Borrow 实体类的增、删、改、查等数据库操作方法。
 */
public interface BorrowMapper {

    /**
     * 新增操作：向 borrow 表中插入一条新的借用记录
     *
     * @param borrow 要插入的 Borrow 对象
     * @return 插入成功的记录数（通常为1）
     */
    int insert(Borrow borrow);

    /**
     * 删除操作：根据给定的 ID 删除 borrow 表中的借用记录
     *
     * @param id 要删除的 Borrow 对象的 ID
     * @return 删除成功的记录数（通常为1）
     */
    int deleteById(Integer id);

    /**
     * 修改操作：根据给定的 Borrow 对象更新 borrow 表中的借用记录
     *
     * @param borrow 包含更新数据的 Borrow 对象
     * @return 更新成功的记录数（通常为1）
     */
    int updateById(Borrow borrow);

    /**
     * 查询操作：根据给定的 ID 查询 borrow 表中的借用记录
     *
     * @param id 要查询的 Borrow 对象的 ID
     * @return 对应 ID 的 Borrow 对象，如果不存在则返回 null
     */
    Borrow selectById(Integer id);

    /**
     * 查询所有操作：查询 borrow 表中的所有借用记录
     *
     * @param borrow 可能包含查询条件的 Borrow 对象（如果查询条件为空，可以传入一个空对象）
     * @return 所有符合条件的 Borrow 对象列表
     */
    List<Borrow> selectAll(Borrow borrow);

    /**
     * 通过申请：批准借用申请，将借用状态设置为通过
     *
     * @param borrow 包含借用申请信息的 Borrow 对象
     * @return 更新成功的记录数（通常为1）
     */
    int pass(Borrow borrow);

    /**
     * 驳回申请：驳回借用申请，将借用状态设置为未通过
     *
     * @param borrow 包含借用申请信息的 Borrow 对象
     * @return 更新成功的记录数（通常为1）
     */
    int noPass(Borrow borrow);

    /**
     * 确认申请完成：确认借用已完成，更新借用状态
     *
     * @param borrow 包含借用信息的 Borrow 对象
     * @return 更新成功的记录数（通常为1）
     */
    int finish(Borrow borrow);

    /**
     * 查询是否已被借用：检查某项资源是否已被借用
     *
     * @param borrow 包含借用资源信息的 Borrow 对象
     * @return 借用记录数（如果该资源已被借用，返回1，否则返回0）
     */
    int isBorrowed(Borrow borrow);
}
