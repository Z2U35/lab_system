package jmu.lx.lab.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域配置类
 *
 *  此配置类用于解决前后端分离项目中的跨域请求问题。
 *  CORS (Cross-Origin Resource Sharing) 是一种机制，允许在浏览器中运行的 Web 应用程序
 *  向与该应用程序自身的来源（域名、协议、端口）不同的域发送请求。
 *  默认情况下，浏览器会阻止这种跨域请求，因为这被认为是潜在的安全风险。
 *
 *  本配置允许来自任何来源的请求访问服务器资源。
 */
@Configuration
public class CorsConfig {

    /**
     *  创建并配置 CorsFilter Bean
     *  此方法用于创建一个 CorsFilter 实例，并配置允许的跨域访问规则。
     *  通过 Spring 的 @Bean 注解，将 CorsFilter 注册为 Spring Bean，
     *  使得 Spring 可以管理和使用此过滤器。
     *
     * @return  返回一个配置好的 CorsFilter 实例
     */
    @Bean
    public CorsFilter corsFilter() {
        // 1. 创建 UrlBasedCorsConfigurationSource 对象，用于注册跨域配置
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 2. 创建 CorsConfiguration 对象，用于配置跨域规则
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // 3. 配置允许的跨域访问规则
        corsConfiguration.addAllowedOrigin("*"); // 允许来自任何来源的请求。 实际项目应该配置允许的具体域名，* 表示允许任何来源是不安全的。
        corsConfiguration.addAllowedHeader("*"); // 允许任何请求头，包括自定义请求头。
        corsConfiguration.addAllowedMethod("*"); // 允许任何 HTTP 方法，包括 GET, POST, PUT, DELETE 等。

        // 4. 将跨域配置注册到 UrlBasedCorsConfigurationSource，并指定应用此配置的路径
        //    "/**" 表示对所有接口都应用此跨域配置
        source.registerCorsConfiguration("/**", corsConfiguration);

        // 5. 使用配置好的跨域规则创建一个 CorsFilter 对象并返回
        return new CorsFilter(source);
    }
}