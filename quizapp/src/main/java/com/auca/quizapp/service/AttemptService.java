package com.auca.quizapp.service;
import com.auca.quizapp.dao.AttemptDao;
import com.auca.quizapp.model.Attempt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttemptService {
    @Autowired
    private AttemptDao attemptDao;

    @Autowired
    private ScoreCalculationService scoreCalculationService;

    public int calculateScore(Attempt attempt) {
        int score = scoreCalculationService.calculateScore(attempt.getQuiz().getId(), attempt.getUserAnswers());
        attempt.setScore(score);
        attemptDao.save(attempt);
        return score;
    }

    public void saveAttempt(Attempt attempt) {
        attemptDao.save(attempt);
    }
}
