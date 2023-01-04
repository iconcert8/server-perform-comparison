package com.gyeom.apachemonolithic;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class AppController {

    private final UserRepository userRepository;

    public AppController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public ResponseEntity<Object> root(){
        Map<String, Object> map = new HashMap<>(){{
            put("status", 200);
        }};
        return ResponseEntity.ok().body(map);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody User user){
        try{
            User savedUser = userRepository.save(user);
            return ResponseEntity.ok().body(savedUser);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Object> read(@PathVariable("id") long id){
        Optional<User> nullableUser = userRepository.findById(id);
        if(nullableUser.isEmpty()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok().body(nullableUser.get());
    }
}
