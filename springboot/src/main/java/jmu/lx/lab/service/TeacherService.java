package jmu.lx.lab.service;

import cn.hutool.core.util.ObjectUtil;
import jmu.lx.lab.common.Constants;
import jmu.lx.lab.common.enums.ResultCodeEnum;
import jmu.lx.lab.common.enums.RoleEnum;
import jmu.lx.lab.entity.Account;
import jmu.lx.lab.entity.Teacher;
import jmu.lx.lab.exception.CustomException;
import jmu.lx.lab.mapper.TeacherMapper;
import jmu.lx.lab.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教师业务处理
 * 该类负责处理与教师相关的业务逻辑，包括增删改查、登录、修改密码等操作。
 */
@Service
public class TeacherService {

    // 注入 TeacherMapper，用于访问数据库
    @Resource
    private TeacherMapper teacherMapper;

    /**
     * 新增教师
     * @param teacher 需要新增的教师实体对象
     * @throws CustomException 如果用户名已存在，则抛出用户已存在异常
     */
    public void add(Teacher teacher) {
        // 查询数据库中是否已存在相同用户名的教师
        Teacher dbTeacher = teacherMapper.selectByUsername(teacher.getUsername());
        if (ObjectUtil.isNotNull(dbTeacher)) {
            // 如果已存在，抛出用户已存在异常
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }
        // 如果密码为空，则设置默认密码
        if (ObjectUtil.isEmpty(teacher.getPassword())) {
            teacher.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }
        // 如果姓名为空，则使用用户名作为姓名
        if (ObjectUtil.isEmpty(teacher.getName())) {
            teacher.setName(teacher.getUsername());
        }
        // 设置用户角色为教师
        teacher.setRole(RoleEnum.TEACHER.name());
        // 调用Mapper层的insert方法将教师信息插入数据库
        teacherMapper.insert(teacher);
    }

    /**
     * 根据ID删除教师
     * @param id 要删除的教师ID
     */
    public void deleteById(Integer id) {
        // 调用Mapper层的deleteById方法根据ID删除教师
        teacherMapper.deleteById(id);
    }

    /**
     * 批量删除教师
     * @param ids 要删除的教师ID列表
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            // 循环调用Mapper层的deleteById方法删除多个教师
            teacherMapper.deleteById(id);
        }
    }

    /**
     * 根据ID修改教师信息
     * @param teacher 包含要修改的教师信息的实体对象，必须包含ID
     */
    public void updateById(Teacher teacher) {
        // 调用Mapper层的updateById方法更新教师信息
        teacherMapper.updateById(teacher);
    }

    /**
     * 根据ID查询教师信息
     * @param id 要查询的教师ID
     * @return 查询到的教师实体对象，如果不存在则返回null
     */
    public Teacher selectById(Integer id) {
        // 调用Mapper层的selectById方法根据ID查询教师
        return teacherMapper.selectById(id);
    }

    /**
     * 查询所有教师
     * @param teacher 查询条件，可以为空，表示查询所有
     * @return 查询到的教师列表
     */
    public List<Teacher> selectAll(Teacher teacher) {
        // 调用Mapper层的selectAll方法查询所有教师
        return teacherMapper.selectAll(teacher);
    }

    /**
     * 分页查询教师
     * @param teacher 查询条件，可以为空，表示查询所有
     * @param pageNum 当前页码，从1开始
     * @param pageSize 每页显示的数量
     * @return 包含分页信息的PageInfo对象
     */
    public PageInfo<Teacher> selectPage(Teacher teacher, Integer pageNum, Integer pageSize) {
        // 使用PageHelper设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        // 查询当前页的教师数据
        List<Teacher> list = teacherMapper.selectAll(teacher);
        // 将查询结果封装成PageInfo对象返回
        return PageInfo.of(list);
    }

    /**
     * 教师登录
     * @param account 包含用户名和密码的账号信息
     * @return 登录成功的教师账号信息，包含token
     * @throws CustomException 如果用户不存在或密码错误，则抛出对应异常
     */
    public Account login(Account account) {
        // 根据用户名查询教师信息
        Account dbTeacher = teacherMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbTeacher)) {
            // 如果用户不存在，抛出用户不存在异常
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbTeacher.getPassword())) {
            // 如果密码错误，抛出用户账号错误异常
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }
        // 生成token，包含用户ID和角色信息
        String tokenData = dbTeacher.getId() + "-" + RoleEnum.TEACHER.name();
        String token = TokenUtils.createToken(tokenData, dbTeacher.getPassword());
        // 将token设置到教师账号信息中
        dbTeacher.setToken(token);
        return dbTeacher;
    }

    /**
     * 修改密码
     * @param account 包含用户名、旧密码和新密码的账号信息
     * @throws CustomException 如果用户不存在、旧密码错误，则抛出对应异常
     */
    public void updatePassword(Account account) {
        // 根据用户名查询教师信息
        Teacher dbTeacher = teacherMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbTeacher)) {
            // 如果用户不存在，抛出用户不存在异常
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbTeacher.getPassword())) {
            // 如果旧密码错误，抛出密码错误异常
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }
        // 设置新密码
        dbTeacher.setPassword(account.getNewPassword());
        // 调用Mapper层的updateById方法更新教师信息
        teacherMapper.updateById(dbTeacher);
    }

    /**
     * 重置密码为默认密码
     * @param id 要重置密码的教师ID
     */
    public void resetPassword(Integer id) {
        // 调用Mapper层的resetPassword方法重置密码
        teacherMapper.resetPassword(id);
    }
}