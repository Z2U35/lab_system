package jmu.lx.lab.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jmu.lx.lab.common.Constants;
import jmu.lx.lab.common.enums.RoleEnum;
import jmu.lx.lab.entity.Account;
import jmu.lx.lab.service.AdminService;
import jmu.lx.lab.service.LabAdminService;
import jmu.lx.lab.service.StudentService;
import jmu.lx.lab.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Token 工具类
 * 功能：
 * 1. 生成 JWT Token；
 * 2. 解析 Token；
 * 3. 根据 Token 获取当前登录用户的信息。
 */
@Component
public class TokenUtils {

    private static final Logger log = LoggerFactory.getLogger(TokenUtils.class); // 日志记录器

    // 静态 Service 对象，用于静态方法中调用
    private static AdminService staticAdminService;
    private static LabAdminService staticLabAdminService;
    private static TeacherService staticTeacherService;
    private static StudentService staticStudentService;

    // 注入 Service 实例，通过 Spring 容器管理
    @Resource
    AdminService adminService;
    @Resource
    LabAdminService labAdminService;
    @Resource
    TeacherService teacherService;
    @Resource
    StudentService studentService;

    /**
     * 使用 @PostConstruct 注解，在 Bean 初始化完成后，
     * 将通过依赖注入获取的 Service 实例赋值给静态变量。
     * 目的是在静态方法中可以使用这些 Service。
     */
    @PostConstruct
    public void setUserService() {
        staticAdminService = adminService;
        staticLabAdminService = labAdminService;
        staticTeacherService = teacherService;
        staticStudentService = studentService;
    }

    /**
     * 生成 JWT Token
     * @param data  数据载荷，存储在 Token 中（如 "用户ID-角色"）
     * @param sign  密钥，用于加密 Token（如用户的密码）
     * @return  返回生成的 JWT Token 字符串
     */
    public static String createToken(String data, String sign) {
        // 使用 JWT 创建 Token，包含数据和过期时间，使用 HMAC256 加密
        return JWT.create()
                .withAudience(data) // 设置数据载荷，例如 "用户ID-角色"
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) // 设置过期时间为当前时间+2小时
                .sign(Algorithm.HMAC256(sign)); // 使用密钥（sign）进行加密
    }

    /**
     * 获取当前登录用户的信息
     * @return  当前登录用户的 Account 对象，如果未登录或出错则返回一个空 Account
     */
    public static Account getCurrentUser() {
        try {
            // 获取当前请求对象
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            // 从请求头中获取 Token
            String token = request.getHeader(Constants.TOKEN);
            if (ObjectUtil.isNotEmpty(token)) {
                // 解析 Token，获取数据载荷
                String userRole = JWT.decode(token).getAudience().get(0); // 获取存储的 "用户ID-角色"
                String userId = userRole.split("-")[0];  // 提取用户ID
                String role = userRole.split("-")[1];    // 提取用户角色
                // 根据角色调用对应的 Service 查询用户信息
                if (RoleEnum.ADMIN.name().equals(role)) {
                    return staticAdminService.selectById(Integer.valueOf(userId)); // 查询管理员信息
                }
                if (RoleEnum.LABADMIN.name().equals(role)) {
                    return staticLabAdminService.selectById(Integer.valueOf(userId)); // 查询实验员信息
                }
                if (RoleEnum.TEACHER.name().equals(role)) {
                    return staticTeacherService.selectById(Integer.valueOf(userId)); // 查询教师信息
                }
                if (RoleEnum.STUDENT.name().equals(role)) {
                    return staticStudentService.selectById(Integer.valueOf(userId)); // 查询学生信息
                }
            }
        } catch (Exception e) {
            // 捕获所有异常并记录错误日志
            log.error("获取当前用户信息出错", e);
        }
        // 如果获取用户信息失败，返回一个空的 Account 对象
        return new Account();
    }
}
