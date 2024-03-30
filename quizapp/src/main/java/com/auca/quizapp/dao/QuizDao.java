package com.auca.quizapp.dao;

import com.auca.quizapp.model.Question;
import com.auca.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QuizDao  extends JpaRepository<Quiz, Integer> {
    List<Quiz> findByCategory(String category);
}
