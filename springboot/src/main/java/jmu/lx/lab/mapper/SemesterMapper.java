package jmu.lx.lab.mapper;

import jmu.lx.lab.entity.Semester;

import java.util.List;

/**
 * 操作semester相关数据接口
*/
public interface SemesterMapper {

    /**
      * 新增
    */
    int insert(Semester semester);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Semester semester);

    /**
      * 根据ID查询
    */
    Semester selectById(Integer id);

    /**
      * 查询所有
    */
    List<Semester> selectAll(Semester semester);

    /**
     * 查询当前学期
     */
    Semester selectCurSemester(Semester semester);

    /**
     * 修改当前学期
     */
    int updateCurSemester(Semester semester);

}