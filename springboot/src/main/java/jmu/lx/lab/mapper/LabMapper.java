package jmu.lx.lab.mapper;

import jmu.lx.lab.entity.Lab;

import java.util.List;

/**
 * 操作lab相关数据接口
*/
public interface LabMapper {

    /**
      * 新增
    */
    int insert(Lab lab);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Lab lab);

    /**
      * 根据ID查询
    */
    Lab selectById(Integer id);

    /**
      * 查询所有
    */
    List<Lab> selectAll(Lab lab);

}