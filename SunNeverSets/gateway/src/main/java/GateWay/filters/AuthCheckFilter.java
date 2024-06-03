package GateWay.filters;



import GateWay.utils.JWTUtils;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import com.alibaba.fastjson.JSON;

import javax.annotation.Resource;
import java.util.Arrays;

@Component
public class AuthCheckFilter implements GlobalFilter {

    @Resource
    private JWTUtils jwtUtils;
//
//    private static final String BEARER_TOKEN_TYPE = "Bearer ";

    @Override
    @Order(-1)
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        String token = exchange.getRequest().getHeaders().getFirst("token");
//        if (shouldSkipFilter(exchange.getRequest())) {
//            return chain.filter(exchange); // 如果请求应该被跳过，则直接放行
//        }
//        if (!StringUtils.hasText(token) || "null".equals(token)) {
//            return unauthorizedResponse(exchange, "token不存在");
//        }
//
//        try {
//            DecodedJWT decodedToken = jwtUtils.verify(token);
//            if (decodedToken != null) {
//                // 可以将验证后的用户信息放入exchange的属性中，供后续使用
//                exchange.getAttributes().put("userId", decodedToken.getClaim("id").asString());
//                return chain.filter(exchange); // 验证成功，继续执行后续过滤器
//            }
//        } catch (JWTVerificationException ex) {
//            return unauthorizedResponse(exchange, "token验证失败： " + ex.getMessage());
//        }
//
//        return unauthorizedResponse(exchange, "token验证失败"); // 验证失败
        return chain.filter(exchange); // 验证成功，继续执行后续过滤器
    }
    // 判断是否需要跳过此过滤器，例如对于某些开放API
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    private boolean shouldSkipFilter(ServerHttpRequest request) {
        // 这里可以根据实际情况定义哪些路径不需要JWT验证
        String path = request.getURI().getPath();
        return Arrays.asList(
                        "/product/**",
                        "/public/**",
                        "/resources/**",
                        "/user/**",
                        "/alipay/**"
                ).stream()
                .anyMatch(pattern -> pathMatcher.match(pattern, path));
    }


    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange, String message) {
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        // 构造JSON对象
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("code", "005");
        jsonResponse.put("msg", message);

        String jsonString = jsonResponse.toJSONString(); // 将JSONObject转换为JSON字符串

        return response.writeWith(Mono.just(response.bufferFactory().wrap(jsonString.getBytes())));
    }
}
