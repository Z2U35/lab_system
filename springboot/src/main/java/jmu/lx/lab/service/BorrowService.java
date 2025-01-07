package jmu.lx.lab.service;

import cn.hutool.core.date.DateUtil;
import jmu.lx.lab.common.enums.ResultCodeEnum;
import jmu.lx.lab.common.enums.RoleEnum;
import jmu.lx.lab.entity.Account;
import jmu.lx.lab.entity.Borrow;
import jmu.lx.lab.exception.CustomException;
import jmu.lx.lab.mapper.BorrowMapper;
import jmu.lx.lab.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 实验室借用表业务处理
 **/
@Service
public class BorrowService {

    @Resource
    private BorrowMapper borrowMapper;
    @Resource
    private ScheduleService scheduleService;

    /**
     * 新增
     */
    public void add(Borrow borrow) {
        int borrowed = borrowMapper.isBorrowed(borrow);
        int scheduled = scheduleService.isScheduled(borrow);
        if (borrowed != 0) {
            throw new CustomException(ResultCodeEnum.LAB_BORROWED);
        }
        if (scheduled != 0) {
            throw new CustomException(ResultCodeEnum.LAB_SCHEDULE);
        }
        borrow.setApplyDate(DateUtil.today());
        borrowMapper.insert(borrow);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        borrowMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            borrowMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Borrow borrow) {
        int borrowed = borrowMapper.isBorrowed(borrow);
        int scheduled = scheduleService.isScheduled(borrow);
        if (borrowed != 0) {
            throw new CustomException(ResultCodeEnum.LAB_BORROWED);
        }
        if (scheduled != 0) {
            throw new CustomException(ResultCodeEnum.LAB_SCHEDULE);
        }
        borrow.setStatus("未审核");
        borrowMapper.updateById(borrow);
    }

    /**
     * 根据ID查询
     */
    public Borrow selectById(Integer id) {
        return borrowMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Borrow> selectAll(Borrow borrow) {
        return borrowMapper.selectAll(borrow);
    }

    /**
     * 分页查询
     */
    public PageInfo<Borrow> selectPage(Borrow borrow, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.STUDENT.name().equals(currentUser.getRole())) {
            borrow.setStudentId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Borrow> list = borrowMapper.selectAll(borrow);
        return PageInfo.of(list);
    }

    /**
     * 通过申请
     */
    public void pass(Borrow borrow) {
        borrowMapper.pass(borrow);
    }

    /**
     * 驳回申请
     */
    public void noPass(Borrow borrow){
        borrowMapper.noPass(borrow);
    }

    /**
     * 确认申请完成
     */
    public void finish(Borrow borrow) {
        borrowMapper.finish(borrow);
    }

    /**
     * 查询是否已被借用中
     */
    public int isBorrowed(Borrow borrow) {
        return borrowMapper.isBorrowed(borrow);
    }

}