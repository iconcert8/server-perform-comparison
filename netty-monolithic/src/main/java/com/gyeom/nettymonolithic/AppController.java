package com.gyeom.nettymonolithic;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AppController {

    private final UserRepository userRepository;

    public AppController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    ResponseEntity<Mono<Object>> root(){
        Map<String, Object> map = new HashMap<>(){{
            put("status", 200);
        }};
        return ResponseEntity.ok().body(Mono.just(map));
    }

    @PostMapping("/create")
    ResponseEntity<Mono<User>> create(@RequestBody User user){
        try{
            Mono<User> savedUser = userRepository.save(user);
            return ResponseEntity.ok().body(savedUser);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/list")
    ResponseEntity<Flux<User>> list(){
        try{
            Flux<User> list = userRepository.findAll();
            return ResponseEntity.ok().body(list);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

}
