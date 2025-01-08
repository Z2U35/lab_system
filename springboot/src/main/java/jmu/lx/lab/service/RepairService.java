package jmu.lx.lab.service;

import cn.hutool.core.date.DateUtil;
import jmu.lx.lab.common.enums.RoleEnum;
import jmu.lx.lab.entity.Account;
import jmu.lx.lab.entity.Repair;
import jmu.lx.lab.mapper.LabMapper;
import jmu.lx.lab.mapper.RepairMapper;
import jmu.lx.lab.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 报修表业务处理
 * 该类负责处理与报修相关的业务逻辑，包括增删改查、维修状态更新等操作。
 */
@Service
public class RepairService {

    // 注入 RepairMapper，用于访问数据库
    @Resource
    private RepairMapper repairMapper;

    // 注入 LabMapper，可能在未来的版本中会用到，目前未使用
    @Resource
    private LabMapper labMapper;

    /**
     * 新增报修记录
     * @param repair  需要新增的报修实体对象
     */
    public void add(Repair repair) {
        // 设置报修申请日期为今天
        repair.setApplyDate(DateUtil.today());
        // 调用Mapper层的insert方法将报修信息插入数据库
        repairMapper.insert(repair);
    }

    /**
     * 根据ID删除报修记录
     * @param id  要删除的报修记录ID
     */
    public void deleteById(Integer id) {
        // 调用Mapper层的deleteById方法根据ID删除报修记录
        repairMapper.deleteById(id);
    }

    /**
     * 批量删除报修记录
     * @param ids  要删除的报修记录ID列表
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            // 循环调用Mapper层的deleteById方法删除多个报修记录
            repairMapper.deleteById(id);
        }
    }

    /**
     * 根据ID修改报修记录信息
     * @param repair  包含要修改的报修信息的实体对象，必须包含ID
     */
    public void updateById(Repair repair) {
        // 调用Mapper层的updateById方法更新报修记录信息
        repairMapper.updateById(repair);
    }

    /**
     * 根据ID查询报修记录
     * @param id  要查询的报修记录ID
     * @return  查询到的报修记录实体对象，如果不存在则返回null
     */
    public Repair selectById(Integer id) {
        // 调用Mapper层的selectById方法根据ID查询报修记录
        return repairMapper.selectById(id);
    }

    /**
     * 查询所有报修记录
     * @param repair 查询条件，可以为空，表示查询所有
     * @return  查询到的报修记录列表
     */
    public List<Repair> selectAll(Repair repair) {
        // 调用Mapper层的selectAll方法查询所有报修记录
        return repairMapper.selectAll(repair);
    }

    /**
     * 分页查询报修记录
     * 根据当前用户角色设置查询条件（教师只能查询自己的报修，实验员只能查询自己负责的报修）
     * @param repair 查询条件，可以为空，表示查询所有
     * @param pageNum  当前页码，从1开始
     * @param pageSize  每页显示的数量
     * @return  包含分页信息的PageInfo对象
     */
    public PageInfo<Repair> selectPage(Repair repair, Integer pageNum, Integer pageSize) {
        // 获取当前登录用户信息
        Account currentUser = TokenUtils.getCurrentUser();
        // 如果当前用户是教师，则设置查询条件为只查询该教师的报修记录
        if (RoleEnum.TEACHER.name().equals(currentUser.getRole())) {
            repair.setTeacherId(currentUser.getId());
        }
        // 如果当前用户是实验员，则设置查询条件为只查询该实验员负责的报修记录
        if (RoleEnum.LABADMIN.name().equals(currentUser.getRole())) {
            repair.setLabadminId(currentUser.getId());
        }
        // 使用PageHelper设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        // 查询当前页的报修记录数据
        List<Repair> list = repairMapper.selectAll(repair);
        // 将查询结果封装成PageInfo对象返回
        return PageInfo.of(list);
    }

    /**
     * 更新报修状态为“维修中”
     * @param repair 包含要更新的报修信息，必须包含ID
     */
    public void repairing(Repair repair) {
        // 调用Mapper层的repairing方法更新报修状态为“维修中”
        repairMapper.repairing(repair);
    }

    /**
     * 更新报修状态为“维修完成”
     * @param repair  包含要更新的报修信息，必须包含ID
     */
    public void repaired(Repair repair) {
        // 调用Mapper层的repaired方法更新报修状态为“维修完成”
        repairMapper.repaired(repair);
    }

}