package com.telusko.quizapp.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AnswerByUserReponse {

    private int id;

    private String rightAnswerByUser;

}
