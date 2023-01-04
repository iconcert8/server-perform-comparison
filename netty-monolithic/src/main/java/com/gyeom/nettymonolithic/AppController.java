package com.gyeom.nettymonolithic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AppController {

    @GetMapping("/")
    Mono<Object> root(){
        Map<String, Object> map = new HashMap<>(){{
            put("status", 200);
        }};
        return Mono.just(map);
    }


}
