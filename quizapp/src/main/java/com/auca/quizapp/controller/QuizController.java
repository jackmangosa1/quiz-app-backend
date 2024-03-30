package com.auca.quizapp.controller;

import com.auca.quizapp.model.Question;
import com.auca.quizapp.model.Quiz;
import com.auca.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;


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
        return quizService.getQuestionsForQuiz(quizId);
    }
}
