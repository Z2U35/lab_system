package jmu.lx.lab.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import jmu.lx.lab.common.Result;
import jmu.lx.lab.common.enums.ResultCodeEnum;
import jmu.lx.lab.common.enums.RoleEnum;
import jmu.lx.lab.entity.Account;
import jmu.lx.lab.service.AdminService;
import jmu.lx.lab.service.LabAdminService;
import jmu.lx.lab.service.StudentService;
import jmu.lx.lab.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 基础前端接口控制器
 *
 *  提供一些基础的前端接口，如首页访问、登录、修改密码等。
 *  使用了 Spring MVC 的 `@RestController` 注解，表明这是一个 RESTful 风格的控制器，
 *  并且所有方法返回的数据都会自动转换为 JSON 格式。
 */
@RestController
public class WebController {

    /**
     * 注入 AdminService
     * 用于处理管理员相关的业务逻辑
     */
    @Resource
    private AdminService adminService;

    /**
     * 注入 LabAdminService
     * 用于处理实验室管理员相关的业务逻辑
     */
    @Resource
    private LabAdminService labAdminService;

    /**
     * 注入 TeacherService
     * 用于处理教师相关的业务逻辑
     */
    @Resource
    private TeacherService teacherService;

    /**
     * 注入 StudentService
     * 用于处理学生相关的业务逻辑
     */
    @Resource
    private StudentService studentService;


    /**
     * 首页访问接口
     *
     *  该接口处理对根路径 "/" 的 GET 请求，返回一个简单的成功消息。
     *
     * @return  返回包含成功消息的 Result 对象
     */
    @GetMapping("/")
    public Result hello() {
        return Result.success("访问成功");
    }


    /**
     * 登录接口
     *
     *  该接口处理对 "/login" 的 POST 请求，用于用户登录。
     *  它接收一个包含用户名、密码和角色信息的 Account 对象，
     *  并根据角色调用不同的 Service 进行登录验证。
     *
     *  @param account  包含用户名、密码和角色信息的 Account 对象
     *  @return  返回包含登录用户信息或错误信息的 Result 对象
     */
    @PostMapping("/login")
    public Result login(@RequestBody Account account) {
        // 1. 验证请求参数是否完整
        if (ObjectUtil.isEmpty(account.getUsername()) || ObjectUtil.isEmpty(account.getPassword())
                || ObjectUtil.isEmpty(account.getRole())) {
            // 如果参数缺失，返回参数丢失错误
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        // 2. 根据用户角色调用相应的 Service 进行登录验证
        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
            account = adminService.login(account);
        }
        if (RoleEnum.LABADMIN.name().equals(account.getRole())) {
            account = labAdminService.login(account);
        }
        if (RoleEnum.TEACHER.name().equals(account.getRole())) {
            account = teacherService.login(account);
        }
        if (RoleEnum.STUDENT.name().equals(account.getRole())) {
            account = studentService.login(account);
        }
        // 3. 返回登录结果
        return Result.success(account);
    }

//  /**
//   * 注册接口 (已注释)
//   *
//   *  该接口处理对 "/register" 的 POST 请求，用于用户注册。
//   *  (此接口目前被注释掉)
//   *
//   *  @param account  包含注册信息的 Account 对象
//   *  @return  返回包含注册结果的 Result 对象
//   */
//  @PostMapping("/register")
//  public Result register(@RequestBody Account account) {
//      if (StrUtil.isBlank(account.getUsername()) || StrUtil.isBlank(account.getPassword())
//              || ObjectUtil.isEmpty(account.getRole())) {
//          return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
//      }
//      if (RoleEnum.ADMIN.name().equals(account.getRole())) {
//          adminService.register(account);
//      }
//      return Result.success();
//  }


    /**
     * 修改密码接口
     *
     *  该接口处理对 "/updatePassword" 的 PUT 请求，用于用户修改密码。
     *  它接收一个包含用户名、旧密码和新密码的 Account 对象，
     *  并根据角色调用不同的 Service 进行密码更新。
     *
     *  @param account  包含用户名、旧密码和新密码的 Account 对象
     *  @return  返回包含修改结果的 Result 对象
     */
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Account account) {
        // 1. 验证请求参数是否完整
        if (StrUtil.isBlank(account.getUsername()) || StrUtil.isBlank(account.getPassword())
                || ObjectUtil.isEmpty(account.getNewPassword())) {
            // 如果参数缺失，返回参数丢失错误
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        // 2. 根据用户角色调用相应的 Service 进行密码更新
        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
            adminService.updatePassword(account);
        }
        if (RoleEnum.LABADMIN.name().equals(account.getRole())) {
            labAdminService.updatePassword(account);
        }
        if (RoleEnum.TEACHER.name().equals(account.getRole())) {
            teacherService.updatePassword(account);
        }
        if (RoleEnum.STUDENT.name().equals(account.getRole())) {
            studentService.updatePassword(account);
        }
        // 3. 返回修改成功的结果
        return Result.success();
    }
}