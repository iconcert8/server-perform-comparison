package com.gyeom.apachemonolithic;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AppController {

    @GetMapping("/")
    public ResponseEntity<Object> root(){
        Map<String, Object> map = new HashMap<>(){{
            put("status", 200);
        }};
        return ResponseEntity.ok().body(map);
    }
}
