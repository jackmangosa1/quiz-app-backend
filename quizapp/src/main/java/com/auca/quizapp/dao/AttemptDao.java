package com.auca.quizapp.dao;

import com.auca.quizapp.model.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttemptDao extends JpaRepository<Attempt, Integer> {

}
