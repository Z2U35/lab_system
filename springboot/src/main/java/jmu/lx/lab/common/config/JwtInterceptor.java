package jmu.lx.lab.common.config;

import cn.hutool.core.util.ObjectUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jmu.lx.lab.common.Constants;
import jmu.lx.lab.common.enums.ResultCodeEnum;
import jmu.lx.lab.common.enums.RoleEnum;
import jmu.lx.lab.entity.Account;
import jmu.lx.lab.exception.CustomException;
import jmu.lx.lab.service.AdminService;
import jmu.lx.lab.service.LabAdminService;
import jmu.lx.lab.service.StudentService;
import jmu.lx.lab.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JWT 拦截器
 * 该拦截器用于验证请求头中携带的 JWT (JSON Web Token)，
 * 并在 token 验证通过后，允许请求访问受保护的资源。
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(JwtInterceptor.class);

    // 注入 AdminService，用于查询管理员信息
    @Resource
    private AdminService adminService;

    // 注入 LabAdminService，用于查询实验员信息
    @Resource
    private LabAdminService labAdminService;

    // 注入 TeacherService，用于查询教师信息
    @Resource
    private TeacherService teacherService;

    // 注入 StudentService，用于查询学生信息
    @Resource
    private StudentService studentService;

    /**
     * 在请求到达 Controller 之前执行的方法
     * @param request  HTTP 请求对象
     * @param response HTTP 响应对象
     * @param handler  处理器对象
     * @return  如果 token 验证通过，返回 true，否则抛出异常
     * @throws CustomException  如果 token 无效或验证失败，则抛出自定义异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 1. 从 HTTP 请求的 header 中获取 token
        String token = request.getHeader(Constants.TOKEN);
        // 如果 header 中没有 token
        if (ObjectUtil.isEmpty(token)) {
            // 从请求参数中尝试获取 token
            token = request.getParameter(Constants.TOKEN);
        }
        // 2. 开始执行认证
        // 如果 token 仍然为空
        if (ObjectUtil.isEmpty(token)) {
            // 抛出 token 无效异常
            throw new CustomException(ResultCodeEnum.TOKEN_INVALID_ERROR);
        }
        Account account = null;
        try {
            // 解析 token 获取存储的用户角色和用户ID
            String userRole = JWT.decode(token).getAudience().get(0);
            String userId = userRole.split("-")[0];
            String role = userRole.split("-")[1];
            // 根据角色和用户ID查询数据库，获取用户信息
            if (RoleEnum.ADMIN.name().equals(role)) {
                account = adminService.selectById(Integer.valueOf(userId));
            }
            if (RoleEnum.LABADMIN.name().equals(role)) {
                account = labAdminService.selectById(Integer.valueOf(userId));
            }
            if (RoleEnum.TEACHER.name().equals(role)) {
                account = teacherService.selectById(Integer.valueOf(userId));
            }
            if (RoleEnum.STUDENT.name().equals(role)) {
                account = studentService.selectById(Integer.valueOf(userId));
            }
        } catch (Exception e) {
            // 解析 token 失败，抛出 token 校验失败异常
            throw new CustomException(ResultCodeEnum.TOKEN_CHECK_ERROR);
        }
        // 如果根据 token 中的用户ID没有查询到用户信息
        if (ObjectUtil.isNull(account)) {
            // 抛出用户不存在异常
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        try {
            // 使用用户密码加签验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(account.getPassword())).build();
            jwtVerifier.verify(token); // 验证 token
        } catch (JWTVerificationException e) {
            // token 验证失败，抛出 token 校验失败异常
            throw new CustomException(ResultCodeEnum.TOKEN_CHECK_ERROR);
        }
        // 如果所有验证通过，则返回 true，允许请求继续执行
        return true;
    }
}