package io.github.iweidujiang.javatricks.trick15.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

/**
 * 请求拦截器：自动注入 traceId 到 MDC，并在请求结束时清理。
 * <p>
 * 功能：
 * 1. 优先从请求头 X-Trace-Id 获取（用于跨服务传递）
 * 2. 若无，则生成新的 traceId（截取前8位，简洁且唯一性足够）
 * 3. 放入 MDC，供日志框架使用
 * 4. 响应头回传 X-Trace-Id，便于前端或下游追踪
 * 5. 请求结束后清理 MDC，防止线程复用导致日志污染
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/10
 */
@Component
public class TraceIdInterceptor implements HandlerInterceptor {
    private static final String TRACE_ID_HEADER = "X-Trace-Id";
    private static final String TRACE_ID_MDC_KEY = "traceId";

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // 尝试从请求头获取 traceId（支持跨服务链路传递）
        String traceId = request.getHeader(TRACE_ID_HEADER);

        // 如果没有，则生成一个新的
        if (traceId == null || traceId.trim().isEmpty()) {
            traceId = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        }

        // 将 traceId 放入 MDC（SLF4J 的诊断上下文）
        MDC.put(TRACE_ID_MDC_KEY, traceId);

        // 回传给客户端或其他服务，形成完整链路
        response.setHeader(TRACE_ID_HEADER, traceId);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        // 【关键】清理 MDC，避免线程池复用时上下文污染
        MDC.clear();
    }
}
