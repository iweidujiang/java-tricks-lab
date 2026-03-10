package io.github.iweidujiang.javatricks.trick15.config;

import io.github.iweidujiang.javatricks.trick15.interceptor.TraceIdInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web 配置类：注册 TraceId 拦截器，使其对所有请求生效。
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/10
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final TraceIdInterceptor traceIdInterceptor;

    public WebConfig(TraceIdInterceptor traceIdInterceptor) {
        this.traceIdInterceptor = traceIdInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 对所有路径 /** 应用 traceId 拦截器
        registry.addInterceptor(traceIdInterceptor).addPathPatterns("/**");
    }
}
