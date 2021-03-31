package vkr.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import vkr.services.AWSDataService;

@Controller
@CrossOrigin(allowCredentials = "true")
public class StartController {
    private final AWSDataService awsDataService;

    public StartController(AWSDataService awsDataService) {
        this.awsDataService = awsDataService;
    }

    @GetMapping(value = "")
    public ResponseEntity<String> test(){
        awsDataService.saveColdStartDataToAmazonS3();
        return ResponseEntity.ok("Все работает!");
    }
}
