package com.telusko.quizapp.controller;


import com.telusko.quizapp.model.AnswerByUserReponse;
import com.telusko.quizapp.model.Question;
import com.telusko.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {


    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
        //    return new ResponseEntity<>("I am here", HttpStatus.OK);
        return quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("get/{quizId}")
    public ResponseEntity<?> getListOfQuestions(@PathVariable("quizId") int id) {
        return quizService.getQuizQuestions(id);
    }

   /* @PostMapping("submit/{id}")
    public ResponseEntity<?> submitQuiz(@PathVariable int id, @RequestBody List<AnswerByUserReponse> answersByUserReponse) {
        System.out.println("id : "+id);
        answersByUserReponse.forEach(System.out::println);
        return quizService.calculateResult(id, answersByUserReponse);
    }*/


    @PostMapping("submit/{id}")
    public ResponseEntity<?> submitQuizWithOutExtraClass(@PathVariable int id, @RequestBody List<Question> answersByUserReponse) {
        System.out.println("id : "+id);
        answersByUserReponse.forEach(System.out::println);
        return quizService.calculateResult(id, answersByUserReponse);
    }
}
