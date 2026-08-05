package com.prismamp.archetype.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        CacheRequestBodyFilter cacheRequestBodyFilter = new CacheRequestBodyFilter();
        ServerWebExchange cachedExchange = cacheRequestBodyFilter.apply(exchange);

        return chain.filter(cachedExchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}