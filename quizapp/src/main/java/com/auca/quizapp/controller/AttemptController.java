package com.auca.quizapp.controller;

import com.auca.quizapp.model.Attempt;
import com.auca.quizapp.service.AttemptService;
import com.auca.quizapp.service.ScoreCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/attempts")
public class AttemptController {
    @Autowired
    private AttemptService attemptService;

    @Autowired
    private ScoreCalculationService scoreCalculationService;

    private static final Logger logger = LoggerFactory.getLogger(AttemptController.class);

    @PostMapping("/submit-attempt")
    public int submitAttempt(@RequestBody Attempt attempt) {
        int quizId = attempt.getQuiz().getId();
        List<String> userAnswers = attempt.getUserAnswers();

        logger.info("Submission received for quiz ID: {}", quizId);


        int score = scoreCalculationService.calculateScore(quizId, userAnswers);

        // Save the attempt with the calculated score
        attempt.setScore(score);
        attemptService.saveAttempt(attempt);

        logger.info("Attempt submitted successfully. Score: {}", score);

        return score;
    }

}