package jmu.lx.lab.mapper;

import jmu.lx.lab.entity.Session;

import java.util.List;

/**
 * 操作session相关数据接口
*/
public interface SessionMapper {

    /**
      * 新增
    */
    int insert(Session session);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Session session);

    /**
      * 根据ID查询
    */
    Session selectById(Integer id);

    /**
      * 查询所有
    */
    List<Session> selectAll(Session session);

}