package jmu.lx.lab.service;

import cn.hutool.core.util.ObjectUtil;
import jmu.lx.lab.common.Constants;
import jmu.lx.lab.common.enums.ResultCodeEnum;
import jmu.lx.lab.common.enums.RoleEnum;
import jmu.lx.lab.entity.Account;
import jmu.lx.lab.entity.LabAdmin;
import jmu.lx.lab.exception.CustomException;
import jmu.lx.lab.mapper.LabAdminMapper;
import jmu.lx.lab.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 实验员业务处理
 **/
@Service
public class LabAdminService {

    @Resource
    private LabAdminMapper labAdminMapper;

    /**
     * 新增
     */
    public void add(LabAdmin labAdmin) {
        LabAdmin dbLabAdmin = labAdminMapper.selectByUsername(labAdmin.getUsername());
        if (ObjectUtil.isNotNull(dbLabAdmin)) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }
        if (ObjectUtil.isEmpty(labAdmin.getPassword())) {
            labAdmin.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }
        if (ObjectUtil.isEmpty(labAdmin.getName())) {
            labAdmin.setName(labAdmin.getUsername());
        }
        labAdmin.setRole(RoleEnum.LABADMIN.name());
        labAdminMapper.insert(labAdmin);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        labAdminMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            labAdminMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(LabAdmin labAdmin) {
        labAdminMapper.updateById(labAdmin);
    }

    /**
     * 根据ID查询
     */
    public LabAdmin selectById(Integer id) {
        return labAdminMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<LabAdmin> selectAll(LabAdmin labAdmin) {
        return labAdminMapper.selectAll(labAdmin);
    }

    /**
     * 分页查询
     */
    public PageInfo<LabAdmin> selectPage(LabAdmin labAdmin, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<LabAdmin> list = labAdminMapper.selectAll(labAdmin);
        return PageInfo.of(list);
    }

    /**
     * 登录
     */
    public Account login(Account account) {
        Account dbLabAdmin = labAdminMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbLabAdmin)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbLabAdmin.getPassword())) {
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }
        // 生成token
        String tokenData = dbLabAdmin.getId() + "-" + RoleEnum.LABADMIN.name();
        String token = TokenUtils.createToken(tokenData, dbLabAdmin.getPassword());
        dbLabAdmin.setToken(token);
        return dbLabAdmin;
    }

//    /**
//     * 注册
//     */
//    public void register(Account account) {
//        LabAdmin labAdmin = new LabAdmin();
//        BeanUtils.copyProperties(account, labAdmin);
//        add(labAdmin);
//    }

    /**
     * 修改密码
     */
    public void updatePassword(Account account) {
        LabAdmin dbLabAdmin = labAdminMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbLabAdmin)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbLabAdmin.getPassword())) {
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }
        dbLabAdmin.setPassword(account.getNewPassword());
        labAdminMapper.updateById(dbLabAdmin);
    }


    /**
     * 重置密码
     */
    public void resetPassword(Integer id) {
        labAdminMapper.resetPassword(id);
    }
}