package jmu.lx.lab.mapper;

import jmu.lx.lab.entity.Borrow;

import java.util.List;

/**
 * 操作borrow相关数据接口
*/
public interface BorrowMapper {

    /**
      * 新增
    */
    int insert(Borrow borrow);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Borrow borrow);

    /**
      * 根据ID查询
    */
    Borrow selectById(Integer id);

    /**
      * 查询所有
    */
    List<Borrow> selectAll(Borrow borrow);

    /**
     * 通过申请
     */
    int pass(Borrow borrow);

    /**
     * 驳回申请
     */
    int noPass(Borrow borrow);

    /**
     * 确认申请完成
     */
    int finish(Borrow borrow);

    /**
     * 查询是否已被借用中
     */
    int isBorrowed(Borrow borrow);
}