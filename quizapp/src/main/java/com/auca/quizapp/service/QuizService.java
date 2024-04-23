package com.auca.quizapp.service;

import com.auca.quizapp.dao.QuizDao;
import com.auca.quizapp.model.Quiz;
import org.springframework.stereotype.Service;
import com.auca.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizDao quizDao;


    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        try {
            List<Quiz> quizzes = quizDao.findAll();
            return new ResponseEntity<>(quizzes, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Quiz> createQuiz(Quiz quiz) {
        try {
            Quiz savedQuiz = quizDao.save(quiz);
            return new ResponseEntity<>(savedQuiz, HttpStatus.CREATED);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Quiz> addQuestionToQuiz(Integer quizId, Question question) {
        try {
            Optional<Quiz> optionalQuiz = quizDao.findById(quizId);
            if (optionalQuiz.isPresent()) {
                Quiz quiz = optionalQuiz.get();
                question.setQuiz(quiz);
                quiz.getQuestions().add(question);
                quizDao.save(quiz);
                return new ResponseEntity<>(quiz, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Quiz>> getQuizzesByCategory(String category) {
        try {
            List<Quiz> quizzes = quizDao.findByCategory(category);
            return new ResponseEntity<>(quizzes, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Quiz> updateQuiz(Integer quizId, Quiz updatedQuiz) {
        try {
            Optional<Quiz> optionalQuiz = quizDao.findById(quizId);
            if (optionalQuiz.isPresent()) {
                Quiz quiz = optionalQuiz.get();
                quiz.setTitle(updatedQuiz.getTitle());
                quiz.setStartTime(updatedQuiz.getStartTime());
                quiz.setEndTime(updatedQuiz.getEndTime());
                quiz.setDurationMinutes(updatedQuiz.getDurationMinutes());
                quiz.setCategory(updatedQuiz.getCategory());
                quiz.setDifficultyLevel(updatedQuiz.getDifficultyLevel());


                Quiz savedQuiz = quizDao.save(quiz);
                return new ResponseEntity<>(savedQuiz, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> deleteQuiz(Integer quizId) {
        try {
            if (quizDao.existsById(quizId)) {
                quizDao.deleteById(quizId);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Question>> getQuestionsForQuiz(Integer quizId) {
        try {
            Optional<Quiz> optionalQuiz = quizDao.findById(quizId);
            if (optionalQuiz.isPresent()) {
                Quiz quiz = optionalQuiz.get();
                List<Question> questions = quiz.getQuestions();
                return new ResponseEntity<>(questions, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Quiz> getQuizById(Integer quizId) {
        Quiz quiz = quizDao.findById(quizId).orElse(null);
        if (quiz != null) {
            return new ResponseEntity<>(quiz, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
