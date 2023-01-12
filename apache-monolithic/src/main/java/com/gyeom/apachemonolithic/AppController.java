package com.gyeom.apachemonolithic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
public class AppController {
    Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private final UserRepository userRepository;

    public AppController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public ResponseEntity<Object> root(){
        return ResponseEntity.ok().body("{status: 200}");
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody User user){
        try{
            User savedUser = userRepository.save(user);
            return ResponseEntity.ok().body(savedUser);
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Object> read(@PathVariable("id") String id){
        try{
            Optional<User> nullableUser = userRepository.findById(id);
            if(nullableUser.isEmpty()) return ResponseEntity.notFound().build();
            else return ResponseEntity.ok().body(nullableUser.get());
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
