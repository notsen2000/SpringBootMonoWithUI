package com.telusko.quizapp.controller;


import com.telusko.quizapp.service.QuestionService;
import com.telusko.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
        //return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }

    @GetMapping("category/{cat}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable("cat") String category) {
        return questionService.getQuestionsByCategory(category);
    }


    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }


    @PutMapping("update")
    public String updateQuestion(@RequestBody Question question) {
        return questionService.updateQuestion(question);
    }


    @DeleteMapping("delete/{id}")
    public String deleteQuestion(@PathVariable("id") int idValue) {
        return questionService.deleteQuestion(idValue);
    }

}
