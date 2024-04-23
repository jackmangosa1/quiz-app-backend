package com.auca.quizapp.controller;

import com.auca.quizapp.model.Question;
import com.auca.quizapp.model.Quiz;
import com.auca.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    private static final Logger logger = LoggerFactory.getLogger(QuizController.class);


    @GetMapping("/all")
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @PostMapping("add")
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        return quizService.createQuiz(quiz);
    }

    @PostMapping("/{quizId}/question")
    public ResponseEntity<Quiz> addQuestionToQuiz(@PathVariable Integer quizId, @RequestBody Question question) {
        return quizService.addQuestionToQuiz(quizId, question);
    }

    @PutMapping("/{quizId}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Integer quizId, @RequestBody Quiz quiz) {
        return quizService.updateQuiz(quizId, quiz);
    }

    @DeleteMapping("/{quizId}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Integer quizId) {
        return quizService.deleteQuiz(quizId);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Quiz>> getQuizzesByCategory(@PathVariable String category){
        return quizService.getQuizzesByCategory(category);
    }

    @GetMapping("/{quizId}/questions")
    public ResponseEntity<List<Question>> getQuestionsForQuiz(@PathVariable Integer quizId) {
        logger.info("Starting quiz with quiz ID: {}", quizId);
        return quizService.getQuestionsForQuiz(quizId);
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Integer quizId) {
        return quizService.getQuizById(quizId);
    }
}
