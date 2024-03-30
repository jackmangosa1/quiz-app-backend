package com.auca.quizapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private Date startTime;
    private Date endTime;
    private int durationMinutes;
    private String difficultyLevel;
    private String category;


    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Question> questions;
}
