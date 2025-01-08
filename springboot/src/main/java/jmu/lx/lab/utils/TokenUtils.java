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
 * 该类负责 JWT 的生成和解析，以及获取当前登录用户信息等操作。
 */
@Component
public class TokenUtils {

    private static final Logger log = LoggerFactory.getLogger(TokenUtils.class);

    // 静态的 Service 变量，用于在静态方法中调用
    private static AdminService staticAdminService;
    private static LabAdminService staticLabAdminService;
    private static TeacherService staticTeacherService;
    private static StudentService staticStudentService;

    // 注入 AdminService
    @Resource
    AdminService adminService;

    // 注入 LabAdminService
    @Resource
    LabAdminService labAdminService;

    // 注入 TeacherService
    @Resource
    TeacherService teacherService;

    // 注入 StudentService
    @Resource
    StudentService studentService;

    /**
     *  通过 @PostConstruct 注解，在 Bean 初始化完成后，将注入的 Service 赋值给静态变量
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
     * @param data  要存储在 Token 中的数据，例如 用户ID-用户角色
     * @param sign  用于加密 Token 的密钥，通常是用户的密码
     * @return 生成的 JWT Token 字符串
     */
    public static String createToken(String data, String sign) {
        // 创建 JWT Token
        return JWT.create()
                .withAudience(data) // 将数据保存到 token 里面，作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) // 设置 token 过期时间为 2 小时后
                .sign(Algorithm.HMAC256(sign)); // 使用 HMAC256 算法，并使用密钥 sign 进行加密
    }

    /**
     * 获取当前登录的用户信息
     * 从请求头中获取 Token，并根据 Token 中存储的用户信息查询数据库，返回用户信息
     * @return  当前登录用户的 Account 对象，如果获取失败或未登录，则返回一个空的 Account 对象
     */
    public static Account getCurrentUser() {
        try {
            // 从 RequestContextHolder 中获取 HttpServletRequest 对象
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            // 从请求头中获取 token
            String token = request.getHeader(Constants.TOKEN);
            if (ObjectUtil.isNotEmpty(token)) {
                // 解析 token，获取用户角色和用户ID
                String userRole = JWT.decode(token).getAudience().get(0);
                String userId = userRole.split("-")[0];  // 获取用户id
                String role = userRole.split("-")[1];    // 获取角色
                // 根据角色信息，调用不同的 Service 查询用户信息
                if (RoleEnum.ADMIN.name().equals(role)) {
                    return staticAdminService.selectById(Integer.valueOf(userId));
                }
                if (RoleEnum.LABADMIN.name().equals(role)) {
                    return staticLabAdminService.selectById(Integer.valueOf(userId));
                }
                if (RoleEnum.TEACHER.name().equals(role)) {
                    return staticTeacherService.selectById(Integer.valueOf(userId));
                }
                if (RoleEnum.STUDENT.name().equals(role)) {
                    return staticStudentService.selectById(Integer.valueOf(userId));
                }
            }
        } catch (Exception e) {
            log.error("获取当前用户信息出错", e); // 记录获取用户信息错误日志
        }
        return new Account();  // 返回空的账号对象
    }
}