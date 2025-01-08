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
 * 该类负责处理与实验员相关的业务逻辑，包括增删改查、登录、修改密码等操作。
 */
@Service
public class LabAdminService {

    // 注入 LabAdminMapper，用于访问数据库
    @Resource
    private LabAdminMapper labAdminMapper;

    /**
     * 新增实验员
     * @param labAdmin 需要新增的实验员实体对象
     * @throws CustomException 如果用户名已存在，则抛出用户已存在异常
     */
    public void add(LabAdmin labAdmin) {
        // 查询数据库中是否已存在相同用户名的实验员
        LabAdmin dbLabAdmin = labAdminMapper.selectByUsername(labAdmin.getUsername());
        if (ObjectUtil.isNotNull(dbLabAdmin)) {
            // 如果已存在，抛出用户已存在异常
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }
        // 如果密码为空，则设置默认密码
        if (ObjectUtil.isEmpty(labAdmin.getPassword())) {
            labAdmin.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }
        // 如果姓名为空，则使用用户名作为姓名
        if (ObjectUtil.isEmpty(labAdmin.getName())) {
            labAdmin.setName(labAdmin.getUsername());
        }
        // 设置用户角色为实验员
        labAdmin.setRole(RoleEnum.LABADMIN.name());
        // 调用Mapper层的insert方法将实验员信息插入数据库
        labAdminMapper.insert(labAdmin);
    }

    /**
     * 根据ID删除实验员
     * @param id  要删除的实验员ID
     */
    public void deleteById(Integer id) {
        // 调用Mapper层的deleteById方法根据ID删除实验员
        labAdminMapper.deleteById(id);
    }

    /**
     * 批量删除实验员
     * @param ids  要删除的实验员ID列表
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            // 循环调用Mapper层的deleteById方法删除多个实验员
            labAdminMapper.deleteById(id);
        }
    }

    /**
     * 根据ID修改实验员信息
     * @param labAdmin  包含要修改的实验员信息的实体对象，必须包含ID
     */
    public void updateById(LabAdmin labAdmin) {
        // 调用Mapper层的updateById方法更新实验员信息
        labAdminMapper.updateById(labAdmin);
    }

    /**
     * 根据ID查询实验员信息
     * @param id  要查询的实验员ID
     * @return 查询到的实验员实体对象，如果不存在则返回null
     */
    public LabAdmin selectById(Integer id) {
        // 调用Mapper层的selectById方法根据ID查询实验员
        return labAdminMapper.selectById(id);
    }

    /**
     * 查询所有实验员
     * @param labAdmin  查询条件，可以为空，表示查询所有
     * @return 查询到的实验员列表
     */
    public List<LabAdmin> selectAll(LabAdmin labAdmin) {
        // 调用Mapper层的selectAll方法查询所有实验员
        return labAdminMapper.selectAll(labAdmin);
    }

    /**
     * 分页查询实验员
     * @param labAdmin  查询条件，可以为空，表示查询所有
     * @param pageNum  当前页码，从1开始
     * @param pageSize  每页显示的数量
     * @return 包含分页信息的PageInfo对象
     */
    public PageInfo<LabAdmin> selectPage(LabAdmin labAdmin, Integer pageNum, Integer pageSize) {
        // 使用PageHelper设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        // 查询当前页的实验员数据
        List<LabAdmin> list = labAdminMapper.selectAll(labAdmin);
        // 将查询结果封装成PageInfo对象返回
        return PageInfo.of(list);
    }

    /**
     * 实验员登录
     * @param account  包含用户名和密码的账号信息
     * @return  登录成功的实验员账号信息，包含token
     * @throws CustomException  如果用户不存在或密码错误，则抛出对应异常
     */
    public Account login(Account account) {
        // 根据用户名查询实验员信息
        Account dbLabAdmin = labAdminMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbLabAdmin)) {
            // 如果用户不存在，抛出用户不存在异常
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbLabAdmin.getPassword())) {
            // 如果密码错误，抛出用户账号错误异常
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }
        // 生成token，包含用户ID和角色信息
        String tokenData = dbLabAdmin.getId() + "-" + RoleEnum.LABADMIN.name();
        String token = TokenUtils.createToken(tokenData, dbLabAdmin.getPassword());
        // 将token设置到实验员账号信息中
        dbLabAdmin.setToken(token);
        return dbLabAdmin;
    }


    /**
     * 修改密码
     * @param account  包含用户名、旧密码和新密码的账号信息
     * @throws CustomException  如果用户不存在、旧密码错误，则抛出对应异常
     */
    public void updatePassword(Account account) {
        // 根据用户名查询实验员信息
        LabAdmin dbLabAdmin = labAdminMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbLabAdmin)) {
            // 如果用户不存在，抛出用户不存在异常
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbLabAdmin.getPassword())) {
            // 如果旧密码错误，抛出密码错误异常
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }
        // 设置新密码
        dbLabAdmin.setPassword(account.getNewPassword());
        // 调用Mapper层的updateById方法更新实验员信息
        labAdminMapper.updateById(dbLabAdmin);
    }


    /**
     * 重置密码为默认密码
     * @param id  要重置密码的实验员ID
     */
    public void resetPassword(Integer id) {
        // 调用Mapper层的resetPassword方法重置密码
        labAdminMapper.resetPassword(id);
    }
}