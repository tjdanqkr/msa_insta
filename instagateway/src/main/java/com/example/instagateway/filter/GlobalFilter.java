package com.example.instagateway.filter;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {
    public GlobalFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            String method = request.getMethod().toString();
            String id = request.getId();
            String path = request.getPath().toString();
            String body;
            if(method == "GET" || method == "DELETE"){
                body = request.getQueryParams().toString();
            }else{
                body = request.getBody().toString();
            }
            if(config.isPreLogger()){
                log.info("[" + id + "] " + method + ", " + path + ", " + body);
            }
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                if(config.isPostLogger()) {
                    log.info("[" + id + "] " + response.getStatusCode());
                }
            }));
        });
    }
    @Data
    public static class Config{
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }

}
