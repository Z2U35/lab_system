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
 **/
@Service
public class RepairService {

    @Resource
    private RepairMapper repairMapper;
    @Resource
    private LabMapper labMapper;

    /**
     * 新增
     */
    public void add(Repair repair) {
        repair.setApplyDate(DateUtil.today());
        repairMapper.insert(repair);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        repairMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            repairMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Repair repair) {
        repairMapper.updateById(repair);
    }

    /**
     * 根据ID查询
     */
    public Repair selectById(Integer id) {
        return repairMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Repair> selectAll(Repair repair) {
        return repairMapper.selectAll(repair);
    }

    /**
     * 分页查询
     */
    public PageInfo<Repair> selectPage(Repair repair, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.TEACHER.name().equals(currentUser.getRole())) {
            repair.setTeacherId(currentUser.getId());
        }
        if (RoleEnum.LABADMIN.name().equals(currentUser.getRole())) {
            repair.setLabadminId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Repair> list = repairMapper.selectAll(repair);
        return PageInfo.of(list);
    }

    /**
     * 维修处理中
     */
    public void repairing(Repair repair) {
        repairMapper.repairing(repair);
    }

    /**
     * 维修完成
     */
    public void repaired(Repair repair) {
        repairMapper.repaired(repair);
    }

}