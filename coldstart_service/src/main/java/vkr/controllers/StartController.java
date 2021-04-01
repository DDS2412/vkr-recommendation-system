package vkr.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin(allowCredentials = "true")
public class StartController {
    @GetMapping(value = "")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Все работает!");
    }
}
