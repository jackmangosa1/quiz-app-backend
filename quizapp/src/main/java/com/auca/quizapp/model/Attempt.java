package com.auca.quizapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Attempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer score;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @ElementCollection
    private List<String> userAnswers;
}
