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
 * 基础前端接口
 */
@RestController
public class WebController {

    @Resource
    private AdminService adminService;
    @Resource
    private LabAdminService labAdminService;
    @Resource
    private TeacherService teacherService;
    @Resource
    private StudentService studentService;

    @GetMapping("/")
    public Result hello() {
        return Result.success("访问成功");
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody Account account) {
        if (ObjectUtil.isEmpty(account.getUsername()) || ObjectUtil.isEmpty(account.getPassword())
                || ObjectUtil.isEmpty(account.getRole())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
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
        return Result.success(account);
    }

//    /**
//     * 注册
//     */
//    @PostMapping("/register")
//    public Result register(@RequestBody Account account) {
//        if (StrUtil.isBlank(account.getUsername()) || StrUtil.isBlank(account.getPassword())
//                || ObjectUtil.isEmpty(account.getRole())) {
//            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
//        }
//        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
//            adminService.register(account);
//        }
//        return Result.success();
//    }

    /**
     * 修改密码
     */
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Account account) {
        if (StrUtil.isBlank(account.getUsername()) || StrUtil.isBlank(account.getPassword())
                || ObjectUtil.isEmpty(account.getNewPassword())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
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
        return Result.success();
    }

}
