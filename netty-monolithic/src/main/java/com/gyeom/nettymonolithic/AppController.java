package com.gyeom.nettymonolithic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@Component
public class AppController {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private final UserRepository userRepository;

    public AppController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Bean
    public RouterFunction<ServerResponse> routes(){
        return RouterFunctions
                .route(RequestPredicates.GET("/"), this::root)
                .andRoute(RequestPredicates.POST("/create"), this::create)
                ;
    }

    public Mono<ServerResponse> root(ServerRequest req){
        return ServerResponse.ok().body(Mono.just("root"), String.class);
    }

    public Mono<ServerResponse> create(ServerRequest req){
        return req
                .bodyToMono(User.class)
                .flatMap(user -> {
                    try {
                        Mono<User> savedUser = userRepository.save(user);
                        return ServerResponse
                                .ok().contentType(MediaType.APPLICATION_JSON).body(savedUser, User.class);
                    }catch (Exception e){
                        log.error(e.getMessage());
                        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                });
    }

}
