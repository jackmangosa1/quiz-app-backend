package com.auca.quizapp.service;

import com.auca.quizapp.dao.QuestionDao;
import com.auca.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            List<Question> questions = questionDao.findAll();
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    public ResponseEntity<Question> addQuestion(Question question) {
        try {
            Question savedQuestion = questionDao.save(question);
            return new ResponseEntity<>(savedQuestion, HttpStatus.CREATED);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Question> deleteQuestion(Integer questionId) {
        try {
            Optional<Question> optionalQuestion = questionDao.findById(questionId);
            if (optionalQuestion.isPresent()) {
                Question deletedQuestion = optionalQuestion.get();
                questionDao.deleteById(questionId);
                return new ResponseEntity<>(deletedQuestion, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Question> updateQuestion(Integer questionId, Question updatedQuestion) {
        try {
            Optional<Question> optionalQuestion = questionDao.findById(questionId);
            if (optionalQuestion.isPresent()) {
                Question existingQuestion = optionalQuestion.get();
                existingQuestion.setQuestionTitle(updatedQuestion.getQuestionTitle());
                existingQuestion.setOption1(updatedQuestion.getOption1());
                existingQuestion.setOption2(updatedQuestion.getOption2());
                existingQuestion.setOption3(updatedQuestion.getOption3());
                existingQuestion.setOption4(updatedQuestion.getOption4());
                existingQuestion.setRightAnswer(updatedQuestion.getRightAnswer());
                questionDao.save(existingQuestion);
                return new ResponseEntity<>(existingQuestion, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
