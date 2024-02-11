package com.telusko.quizapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Question {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

 //   @JsonProperty("rightAnswerByUser")
    private String right_answer;
    private String category, difficultylevel, option1, option2, option3, option4, question_title;
}
