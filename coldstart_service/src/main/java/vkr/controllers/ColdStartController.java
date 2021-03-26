package vkr.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vkr.dto.ColdStartAnswer;
import vkr.dto.ColdStartEventInfoDto;
import vkr.dto.ColdStartEventsDto;
import vkr.exceptions.EventNotFundException;
import vkr.exceptions.WrongFavoriteIdException;
import vkr.services.ColdStartService;

import java.util.List;

@Controller
@RequestMapping(value = "/coldstart", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@CrossOrigin(allowCredentials = "true")
public class ColdStartController {
    private final ColdStartService coldStartService;

    public ColdStartController(ColdStartService coldStartService) {
        this.coldStartService = coldStartService;
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

    @PostMapping(value = "/save")
    public ResponseEntity saveUserAnswers(){
        coldStartService.saveColdStartAnswers();
        return ResponseEntity.ok().build();
    }
}
