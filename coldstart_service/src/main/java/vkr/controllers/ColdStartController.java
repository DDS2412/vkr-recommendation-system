package vkr.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vkr.dto.ColdStartAnswer;
import vkr.dto.ColdStartEventsDto;
import vkr.exceptions.EventNotFundException;
import vkr.exceptions.WrongFavoriteIdException;
import vkr.services.AWSDataService;
import vkr.services.ColdStartService;

import java.util.List;

@Controller
@RequestMapping(value = "/coldstart", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@CrossOrigin(allowCredentials = "true")
public class ColdStartController {
    private final ColdStartService coldStartService;
    private final AWSDataService awsDataService;
    private final String password;

    public ColdStartController(ColdStartService coldStartService,
                               AWSDataService awsDataService,
                               String password) {
        this.coldStartService = coldStartService;
        this.awsDataService = awsDataService;
        this.password = password;
    }

    @GetMapping(value = "/events")
    public ResponseEntity<ColdStartEventsDto> getAllEventCategories(){
        return ResponseEntity.ok(coldStartService.getColdStartEvents());
    }

    @PostMapping(value = "/all/answers/user/{user_id}")
    public ResponseEntity setUserAnswersForColdStart(@RequestBody List<ColdStartAnswer> coldStartAnswers,
                                                     @PathVariable("user_id") Integer userId) throws EventNotFundException, WrongFavoriteIdException {
        try {
            coldStartService.setUserAnswersForColdStart(coldStartAnswers, userId);
        } catch (EventNotFundException | WrongFavoriteIdException e){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/answers/user/{user_id}")
    public ResponseEntity setUserAnswerForColdStart(@RequestBody ColdStartAnswer coldStartAnswers,
                                                @PathVariable("user_id") Integer userId) throws EventNotFundException, WrongFavoriteIdException {

        try {
            coldStartService.setUserAnswerForColdStart(coldStartAnswers, userId);
        } catch (EventNotFundException | WrongFavoriteIdException e){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/save/{password}")
    public ResponseEntity saveUserAnswers(@PathVariable("password") String password){
        if (password.equals(this.password)){
            awsDataService.saveColdStartDataToAmazonS3();
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
