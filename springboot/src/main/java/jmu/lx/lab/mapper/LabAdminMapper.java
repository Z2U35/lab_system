package jmu.lx.lab.mapper;

import jmu.lx.lab.entity.LabAdmin;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作LabAdmin相关数据接口
*/
public interface LabAdminMapper {

    /**
      * 新增
    */
    int insert(LabAdmin labAdmin);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(LabAdmin labAdmin);

    /**
      * 根据ID查询
    */
    LabAdmin selectById(Integer id);

    /**
      * 查询所有
    */
    List<LabAdmin> selectAll(LabAdmin labAdmin);

    @Select("select * from lab_admin where username = #{username}")
    LabAdmin selectByUsername(String username);


    /**
     * 重置密码
     */
    int resetPassword(Integer id);
}