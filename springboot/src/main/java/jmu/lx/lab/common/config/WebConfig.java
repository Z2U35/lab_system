package jmu.lx.lab.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * Web 配置类
 *
 *  该配置类用于配置 Spring MVC 的行为，例如添加自定义拦截器。
 *  它实现了 WebMvcConfigurer 接口，允许我们自定义 Spring MVC 的配置。
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 注入 JwtInterceptor
     *
     *  `@Resource` 注解用于注入一个名为 `jwtInterceptor` 的 Bean 对象，
     *  这个 Bean 对象是自定义的拦截器，用于在请求到达 Controller 之前进行身份验证。
     */
    @Resource
    private JwtInterceptor jwtInterceptor;

    /**
     * 添加自定义拦截器
     *
     *  `addInterceptors` 方法用于配置 Spring MVC 的拦截器，它接受一个 `InterceptorRegistry` 对象，
     *  用于注册拦截器和设置拦截规则。
     *
     *  @param registry 拦截器注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 1. 添加 jwtInterceptor 拦截器
        registry.addInterceptor(jwtInterceptor)
                // 2. 设置拦截路径规则: "/**" 表示拦截所有请求
                .addPathPatterns("/**")
                // 3. 设置排除拦截路径规则: 排除根路径("/")
                .excludePathPatterns("/")
                // 4. 设置排除拦截路径规则: 排除登录请求("/login")
                .excludePathPatterns("/login")
                // 5. 设置排除拦截路径规则: 排除静态资源请求("/files/**")
                .excludePathPatterns("/files/**");
    }
}