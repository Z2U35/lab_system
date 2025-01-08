package jmu.lx.lab.service;

import cn.hutool.core.util.ObjectUtil;
import jmu.lx.lab.common.Constants;
import jmu.lx.lab.common.enums.ResultCodeEnum;
import jmu.lx.lab.common.enums.RoleEnum;
import jmu.lx.lab.entity.Account;
import jmu.lx.lab.entity.Student;
import jmu.lx.lab.exception.CustomException;
import jmu.lx.lab.mapper.StudentMapper;
import jmu.lx.lab.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 学生业务处理
 * 该类负责处理与学生相关的业务逻辑，包括增删改查、登录、修改密码等操作。
 */
@Service
public class StudentService {

    // 注入 StudentMapper，用于访问数据库
    @Resource
    private StudentMapper studentMapper;

    /**
     * 新增学生
     * @param student  需要新增的学生实体对象
     * @throws CustomException  如果用户名已存在，则抛出用户已存在异常
     */
    public void add(Student student) {
        // 查询数据库中是否已存在相同用户名的学生
        Student dbStudent = studentMapper.selectByUsername(student.getUsername());
        if (ObjectUtil.isNotNull(dbStudent)) {
            // 如果已存在，抛出用户已存在异常
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }
        // 如果密码为空，则设置默认密码
        if (ObjectUtil.isEmpty(student.getPassword())) {
            student.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }
        // 如果姓名为空，则使用用户名作为姓名
        if (ObjectUtil.isEmpty(student.getName())) {
            student.setName(student.getUsername());
        }
        // 设置用户角色为学生
        student.setRole(RoleEnum.STUDENT.name());
        // 调用Mapper层的insert方法将学生信息插入数据库
        studentMapper.insert(student);
    }

    /**
     * 根据ID删除学生
     * @param id  要删除的学生ID
     */
    public void deleteById(Integer id) {
        // 调用Mapper层的deleteById方法根据ID删除学生
        studentMapper.deleteById(id);
    }

    /**
     * 批量删除学生
     * @param ids  要删除的学生ID列表
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            // 循环调用Mapper层的deleteById方法删除多个学生
            studentMapper.deleteById(id);
        }
    }

    /**
     * 根据ID修改学生信息
     * @param student  包含要修改的学生信息的实体对象，必须包含ID
     */
    public void updateById(Student student) {
        // 调用Mapper层的updateById方法更新学生信息
        studentMapper.updateById(student);
    }

    /**
     * 根据ID查询学生信息
     * @param id  要查询的学生ID
     * @return  查询到的学生实体对象，如果不存在则返回null
     */
    public Student selectById(Integer id) {
        // 调用Mapper层的selectById方法根据ID查询学生
        return studentMapper.selectById(id);
    }

    /**
     * 查询所有学生
     * @param student  查询条件，可以为空，表示查询所有
     * @return 查询到的学生列表
     */
    public List<Student> selectAll(Student student) {
        // 调用Mapper层的selectAll方法查询所有学生
        return studentMapper.selectAll(student);
    }

    /**
     * 分页查询学生
     * @param student  查询条件，可以为空，表示查询所有
     * @param pageNum  当前页码，从1开始
     * @param pageSize  每页显示的数量
     * @return  包含分页信息的PageInfo对象
     */
    public PageInfo<Student> selectPage(Student student, Integer pageNum, Integer pageSize) {
        // 使用PageHelper设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        // 查询当前页的学生数据
        List<Student> list = studentMapper.selectAll(student);
        // 将查询结果封装成PageInfo对象返回
        return PageInfo.of(list);
    }

    /**
     * 学生登录
     * @param account  包含用户名和密码的账号信息
     * @return 登录成功的学生账号信息，包含token
     * @throws CustomException  如果用户不存在或密码错误，则抛出对应异常
     */
    public Account login(Account account) {
        // 根据用户名查询学生信息
        Account dbStudent = studentMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbStudent)) {
            // 如果用户不存在，抛出用户不存在异常
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbStudent.getPassword())) {
            // 如果密码错误，抛出用户账号错误异常
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }
        // 生成token，包含用户ID和角色信息
        String tokenData = dbStudent.getId() + "-" + RoleEnum.STUDENT.name();
        String token = TokenUtils.createToken(tokenData, dbStudent.getPassword());
        // 将token设置到学生账号信息中
        dbStudent.setToken(token);
        return dbStudent;
    }


    /**
     * 修改密码
     * @param account  包含用户名、旧密码和新密码的账号信息
     * @throws CustomException  如果用户不存在、旧密码错误，则抛出对应异常
     */
    public void updatePassword(Account account) {
        // 根据用户名查询学生信息
        Student dbStudent = studentMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbStudent)) {
            // 如果用户不存在，抛出用户不存在异常
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbStudent.getPassword())) {
            // 如果旧密码错误，抛出密码错误异常
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }
        // 设置新密码
        dbStudent.setPassword(account.getNewPassword());
        // 调用Mapper层的updateById方法更新学生信息
        studentMapper.updateById(dbStudent);
    }

    /**
     * 重置密码为默认密码
     * @param id  要重置密码的学生ID
     */
    public void resetPassword(Integer id) {
        // 调用Mapper层的resetPassword方法重置密码
        studentMapper.resetPassword(id);
    }

}