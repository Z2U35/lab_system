package jmu.lx.lab.service;

import cn.hutool.core.util.ObjectUtil;
import jmu.lx.lab.common.Constants;
import jmu.lx.lab.common.enums.ResultCodeEnum;
import jmu.lx.lab.common.enums.RoleEnum;
import jmu.lx.lab.entity.Account;
import jmu.lx.lab.entity.Admin;
import jmu.lx.lab.exception.CustomException;
import jmu.lx.lab.mapper.AdminMapper;
import jmu.lx.lab.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 管理员业务处理
 * 提供管理员的新增、删除、修改、查询、分页查询、登录和密码更新等功能。
 */
@Service
public class AdminService {

    // 注入AdminMapper，用于与数据库交互
    @Resource
    private AdminMapper adminMapper;

    /**
     * 新增管理员
     * @param admin 管理员对象
     * 检查用户名是否重复，设置默认值（密码、名称、角色），然后插入数据库。
     */
    public void add(Admin admin) {
        // 根据用户名检查数据库中是否已有相同的管理员
        Admin dbAdmin = adminMapper.selectByUsername(admin.getUsername());
        if (ObjectUtil.isNotNull(dbAdmin)) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR); // 用户已存在异常
        }
        // 如果密码为空，则设置默认密码
        if (ObjectUtil.isEmpty(admin.getPassword())) {
            admin.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }
        // 如果名称为空，则使用用户名作为默认名称
        if (ObjectUtil.isEmpty(admin.getName())) {
            admin.setName(admin.getUsername());
        }
        // 设置管理员角色
        admin.setRole(RoleEnum.ADMIN.name());
        // 将管理员插入数据库
        adminMapper.insert(admin);
    }

    /**
     * 删除管理员
     * @param id 管理员ID
     * 根据管理员ID从数据库中删除对应记录。
     */
    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }

    /**
     * 批量删除管理员
     * @param ids 管理员ID列表
     * 遍历ID列表，逐一删除对应的管理员记录。
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            adminMapper.deleteById(id);
        }
    }

    /**
     * 更新管理员信息
     * @param admin 管理员对象
     * 根据管理员ID更新数据库中的管理员信息。
     */
    public void updateById(Admin admin) {
        adminMapper.updateById(admin);
    }

    /**
     * 根据ID查询管理员信息
     * @param id 管理员ID
     * @return 返回对应管理员对象
     */
    public Admin selectById(Integer id) {
        return adminMapper.selectById(id);
    }

    /**
     * 查询所有管理员信息
     * @param admin 查询条件封装的管理员对象
     * @return 符合条件的管理员列表
     */
    public List<Admin> selectAll(Admin admin) {
        return adminMapper.selectAll(admin);
    }

    /**
     * 分页查询管理员信息
     * @param admin 查询条件封装的管理员对象
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @return 分页结果封装的PageInfo对象
     */
    public PageInfo<Admin> selectPage(Admin admin, Integer pageNum, Integer pageSize) {
        // 开始分页
        PageHelper.startPage(pageNum, pageSize);
        // 查询符合条件的管理员列表
        List<Admin> list = adminMapper.selectAll(admin);
        // 将结果封装成分页对象返回
        return PageInfo.of(list);
    }

    /**
     * 管理员登录
     * @param account 帐号对象，包含用户名、密码和角色
     * @return 登录成功后返回管理员账户信息，包含生成的JWT Token
     */
    public Account login(Account account) {
        // 根据用户名查询管理员信息
        Account dbAdmin = adminMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbAdmin)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR); // 用户不存在异常
        }
        // 校验密码是否正确
        if (!account.getPassword().equals(dbAdmin.getPassword())) {
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR); // 账号或密码错误异常
        }
        // 生成JWT Token，用于后续接口访问认证
        String tokenData = dbAdmin.getId() + "-" + RoleEnum.ADMIN.name();
        String token = TokenUtils.createToken(tokenData, dbAdmin.getPassword());
        dbAdmin.setToken(token); // 将Token赋值给返回的账户对象
        return dbAdmin;
    }

    /**
     * 修改管理员密码
     * @param account 帐号对象，包含用户名、旧密码和新密码
     * 验证旧密码正确后，更新为新密码。
     */
    public void updatePassword(Account account) {
        // 根据用户名查询管理员信息
        Admin dbAdmin = adminMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbAdmin)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR); // 用户不存在异常
        }
        // 校验旧密码是否正确
        if (!account.getPassword().equals(dbAdmin.getPassword())) {
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR); // 原密码错误异常
        }
        // 更新为新密码
        dbAdmin.setPassword(account.getNewPassword());
        adminMapper.updateById(dbAdmin);
    }

}
