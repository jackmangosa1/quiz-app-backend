package com.auca.quizapp.controller;
import com.auca.quizapp.model.Attempt;
import com.auca.quizapp.model.Quiz;
import com.auca.quizapp.service.AttemptService;
import com.auca.quizapp.service.ScoreCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attempts")

public class AttemptController {
    @Autowired
    private AttemptService attemptService;

    @PostMapping("/submit-attempt")
    public int calculateScoreAndSaveAttempt(@RequestBody Attempt attempt) {
        int score = attemptService.calculateScore(attempt);
        return score;
    }
}
