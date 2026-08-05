package com.prismamp.archetype.filter;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebExchangeDecorator;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class CacheRequestBodyFilter implements Function<ServerWebExchange, ServerWebExchange> {

    @Override
    public ServerWebExchange apply(ServerWebExchange exchange) {
        return new CacheRequestBodyServerWebExchangeDecorator(exchange);
    }

    private static class CacheRequestBodyServerWebExchangeDecorator extends ServerWebExchangeDecorator {

        private final ServerHttpRequestDecorator requestDecorator;

        public CacheRequestBodyServerWebExchangeDecorator(ServerWebExchange delegate) {
            super(delegate);
            this.requestDecorator = new CachedBodyHttpRequestDecorator(delegate);
        }

        @Override
        public ServerHttpRequest getRequest() {
            return this.requestDecorator;
        }

        private static class CachedBodyHttpRequestDecorator extends ServerHttpRequestDecorator {

            private final Flux<DataBuffer> cachedBody;

            public CachedBodyHttpRequestDecorator(ServerWebExchange exchange) {
                super(exchange.getRequest());
                cachedBody = cacheRequestBody(exchange);
            }

            private Flux<DataBuffer> cacheRequestBody(ServerWebExchange exchange) {
                return DataBufferUtils.join(exchange.getRequest().getBody())
                        .flatMapMany(dataBuffer -> {
                            DataBuffer cachedBuffer = dataBufferFactory().allocateBuffer(dataBuffer.readableByteCount());
                            cachedBuffer.write(dataBuffer);
                            DataBufferUtils.release(dataBuffer);
                            return Flux.just(cachedBuffer);
                        })
                        .cache();
            }

            @Override
            public Flux<DataBuffer> getBody() {
                return cachedBody;
            }

            private DefaultDataBufferFactory dataBufferFactory() {
                return new DefaultDataBufferFactory();
            }
        }
    }
}