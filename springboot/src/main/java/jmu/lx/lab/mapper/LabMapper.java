package jmu.lx.lab.mapper;

import jmu.lx.lab.entity.Lab;

import java.util.List;

/**
 * 操作 lab 表相关数据的 Mapper 接口
 * 提供对 Lab 实体类的增、删、改、查等数据库操作方法。
 */
public interface LabMapper {

    /**
     * 新增操作：向 lab 表中插入一条新的实验室记录
     *
     * @param lab 要插入的 Lab 对象
     * @return 插入成功的记录数（通常为1）
     */
    int insert(Lab lab);

    /**
     * 删除操作：根据给定的 ID 删除 lab 表中的实验室记录
     *
     * @param id 要删除的 Lab 对象的 ID
     * @return 删除成功的记录数（通常为1）
     */
    int deleteById(Integer id);

    /**
     * 修改操作：根据给定的 Lab 对象更新 lab 表中的实验室记录
     *
     * @param lab 包含更新数据的 Lab 对象
     * @return 更新成功的记录数（通常为1）
     */
    int updateById(Lab lab);

    /**
     * 查询操作：根据给定的 ID 查询 lab 表中的实验室记录
     *
     * @param id 要查询的 Lab 对象的 ID
     * @return 对应 ID 的 Lab 对象，如果不存在则返回 null
     */
    Lab selectById(Integer id);

    /**
     * 查询所有操作：查询 lab 表中的所有实验室记录
     *
     * @param lab 可能包含查询条件的 Lab 对象（如果查询条件为空，可以传入一个空对象）
     * @return 所有符合条件的 Lab 对象列表
     */
    List<Lab> selectAll(Lab lab);
}
