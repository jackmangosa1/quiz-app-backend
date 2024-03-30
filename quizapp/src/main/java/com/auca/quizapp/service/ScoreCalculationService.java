package com.auca.quizapp.service;
import com.auca.quizapp.dao.QuizDao;
import com.auca.quizapp.model.Question;
import com.auca.quizapp.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class ScoreCalculationService {
    @Autowired
    private QuizDao quizDao;

    public int calculateScore(int quizId, List<String> userAnswers) {
        int score = 0;
        Quiz quiz = quizDao.findById(quizId).orElse(null);
        if (quiz == null) {
            return -1; // Indicate quiz not found
        }
        List<Question> questions = quiz.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            if (question.getRightAnswer().equals(userAnswers.get(i))) {
                score++;
            }
        }
        return score;
    }
}
