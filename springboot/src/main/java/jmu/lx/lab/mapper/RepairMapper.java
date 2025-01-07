package jmu.lx.lab.mapper;

import jmu.lx.lab.entity.Repair;

import java.util.List;

/**
 * 操作repair相关数据接口
*/
public interface RepairMapper {

    /**
      * 新增
    */
    int insert(Repair repair);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Repair repair);

    /**
      * 根据ID查询
    */
    Repair selectById(Integer id);

    /**
      * 查询所有
    */
    List<Repair> selectAll(Repair repair);

    /**
     * 报修处理中
     */
    int repairing(Repair repair);

    /**
     * 报修完成
     */
    int repaired(Repair repair);

}