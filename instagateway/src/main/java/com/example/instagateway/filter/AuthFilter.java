package com.example.instagateway.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.xml.bind.DatatypeConverter;

@Component
@Slf4j
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {
    public AuthFilter(){
        super(AuthFilter.Config.class);
    }
    @Value("${jwt.SECRET_KEY}")
    String SECRET_KEY;

    @Override
    public GatewayFilter apply (Config config){
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);
            }
            String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String jwt = authorizationHeader.replace("Bearer " ,"");
            if(!isJwtValid(jwt)){
                return onError(exchange, "Invalid token", HttpStatus.UNAUTHORIZED);
            }
            return chain.filter(exchange);
        });

    }

    private boolean isJwtValid(String jwt) {
        boolean returnValue = true;
        String subject = null;
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
            subject = claims.getSubject();
        }catch (Exception err){
            returnValue = false;
        }
        if(subject==null|| subject.isEmpty()){
            returnValue = false;
        }

        return returnValue;
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        log.error(err);
        return response.setComplete();
    }



    public static class Config{

    }

}
