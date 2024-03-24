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
        questionService.addQuestion(question);
        return ResponseEntity.ok().body("{\"message\": \"Success\"}");
    }


    @PutMapping("update")
    public ResponseEntity<String> updateQuestion(@RequestBody Question question) {


        questionService.updateQuestion(question);
        return ResponseEntity.ok().body("{\"message\": \"Record Updated\"}");
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable("id") int idValue) {
        System.out.println("Id value is deleie : "+idValue);
         questionService.deleteQuestion(idValue);
       // return questionService.deleteQuestion(idValue);
        return ResponseEntity.ok().body("{\"message\": \"Record Deleted\"}");
    }

}
