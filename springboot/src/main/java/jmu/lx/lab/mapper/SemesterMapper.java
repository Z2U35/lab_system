package jmu.lx.lab.mapper;

import jmu.lx.lab.entity.Semester;

import java.util.List;

/**
 * 操作 semester 表相关数据的 Mapper 接口
 * 提供对 Semester 实体类的增、删、改、查以及学期管理相关操作方法。
 */
public interface SemesterMapper {

    /**
     * 新增操作：向 semester 表中插入一条新的学期记录
     *
     * @param semester 要插入的 Semester 对象
     * @return 插入成功的记录数（通常为1）
     */
    int insert(Semester semester);

    /**
     * 删除操作：根据给定的 ID 删除 semester 表中的学期记录
     *
     * @param id 要删除的 Semester 对象的 ID
     * @return 删除成功的记录数（通常为1）
     */
    int deleteById(Integer id);

    /**
     * 修改操作：根据给定的 Semester 对象更新 semester 表中的学期记录
     *
     * @param semester 包含更新数据的 Semester 对象
     * @return 更新成功的记录数（通常为1）
     */
    int updateById(Semester semester);

    /**
     * 查询操作：根据给定的 ID 查询 semester 表中的学期记录
     *
     * @param id 要查询的 Semester 对象的 ID
     * @return 对应 ID 的 Semester 对象，如果不存在则返回 null
     */
    Semester selectById(Integer id);

    /**
     * 查询所有操作：查询 semester 表中的所有学期记录
     *
     * @param semester 可能包含查询条件的 Semester 对象（如果查询条件为空，可以传入一个空对象）
     * @return 所有符合条件的 Semester 对象列表
     */
    List<Semester> selectAll(Semester semester);

    /**
     * 查询当前学期：根据给定条件查询当前正在进行的学期
     *
     * @param semester 包含查询条件的 Semester 对象
     * @return 当前学期的 Semester 对象，如果不存在则返回 null
     */
    Semester selectCurSemester(Semester semester);

    /**
     * 修改当前学期：更新 semester 表中当前学期的标识
     *
     * @param semester 包含更新数据的 Semester 对象
     * @return 更新成功的记录数（通常为1）
     */
    int updateCurSemester(Semester semester);
}
