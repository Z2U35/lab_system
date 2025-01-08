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
 * 提供借用记录的新增、删除、修改、查询等功能。
 */
@Service
public class BorrowService {

    // 注入BorrowMapper用于操作数据库的借用记录
    @Resource
    private BorrowMapper borrowMapper;

    // 注入ScheduleService用于验证排课冲突
    @Resource
    private ScheduleService scheduleService;

    /**
     * 新增实验室借用记录
     * @param borrow 借用记录对象
     * 检查实验室是否已被借用或已排课，若无冲突则插入记录。
     */
    public void add(Borrow borrow) {
        // 检查实验室是否已被借用
        int borrowed = borrowMapper.isBorrowed(borrow);
        // 检查实验室是否已被排课
        int scheduled = scheduleService.isScheduled(borrow);
        if (borrowed != 0) {
            throw new CustomException(ResultCodeEnum.LAB_BORROWED); // 抛出实验室已被借用异常
        }
        if (scheduled != 0) {
            throw new CustomException(ResultCodeEnum.LAB_SCHEDULE); // 抛出实验室已被排课异常
        }
        // 设置申请日期为当天
        borrow.setApplyDate(DateUtil.today());
        // 插入借用记录到数据库
        borrowMapper.insert(borrow);
    }

    /**
     * 根据ID删除借用记录
     * @param id 借用记录ID
     */
    public void deleteById(Integer id) {
        borrowMapper.deleteById(id);
    }

    /**
     * 批量删除借用记录
     * @param ids 借用记录ID列表
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            borrowMapper.deleteById(id);
        }
    }

    /**
     * 修改借用记录
     * @param borrow 借用记录对象
     * 检查实验室是否已被借用或已排课，然后更新记录。
     */
    public void updateById(Borrow borrow) {
        // 检查实验室是否已被借用
        int borrowed = borrowMapper.isBorrowed(borrow);
        // 检查实验室是否已被排课
        int scheduled = scheduleService.isScheduled(borrow);
        if (borrowed != 0) {
            throw new CustomException(ResultCodeEnum.LAB_BORROWED); // 抛出实验室已被借用异常
        }
        if (scheduled != 0) {
            throw new CustomException(ResultCodeEnum.LAB_SCHEDULE); // 抛出实验室已被排课异常
        }
        // 设置状态为未审核
        borrow.setStatus("未审核");
        // 更新借用记录
        borrowMapper.updateById(borrow);
    }

    /**
     * 根据ID查询借用记录
     * @param id 借用记录ID
     * @return 借用记录对象
     */
    public Borrow selectById(Integer id) {
        return borrowMapper.selectById(id);
    }

    /**
     * 查询所有借用记录
     * @param borrow 查询条件封装的借用记录对象
     * @return 借用记录列表
     */
    public List<Borrow> selectAll(Borrow borrow) {
        return borrowMapper.selectAll(borrow);
    }

    /**
     * 分页查询借用记录
     * @param borrow 查询条件封装的借用记录对象
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @return 分页结果封装的PageInfo对象
     */
    public PageInfo<Borrow> selectPage(Borrow borrow, Integer pageNum, Integer pageSize) {
        // 获取当前登录用户
        Account currentUser = TokenUtils.getCurrentUser();
        // 如果是学生角色，仅查询该学生的借用记录
        if (RoleEnum.STUDENT.name().equals(currentUser.getRole())) {
            borrow.setStudentId(currentUser.getId());
        }
        // 开始分页
        PageHelper.startPage(pageNum, pageSize);
        // 查询符合条件的借用记录
        List<Borrow> list = borrowMapper.selectAll(borrow);
        // 将结果封装成分页对象返回
        return PageInfo.of(list);
    }

    /**
     * 通过借用申请
     * @param borrow 借用记录对象
     */
    public void pass(Borrow borrow) {
        borrowMapper.pass(borrow);
    }

    /**
     * 驳回借用申请
     * @param borrow 借用记录对象
     */
    public void noPass(Borrow borrow) {
        borrowMapper.noPass(borrow);
    }

    /**
     * 确认借用申请完成
     * @param borrow 借用记录对象
     */
    public void finish(Borrow borrow) {
        borrowMapper.finish(borrow);
    }

    /**
     * 检查实验室是否已被借用
     * @param borrow 借用记录对象
     * @return 借用状态，0表示未借用，非0表示已被借用
     */
    public int isBorrowed(Borrow borrow) {
        return borrowMapper.isBorrowed(borrow);
    }

}
