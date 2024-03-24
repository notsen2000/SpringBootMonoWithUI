package com.telusko.quizapp.service;

import com.telusko.quizapp.dao.QuestionDAO;
import com.telusko.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDAO questionDAO;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDAO.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        //new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        //return questionDAO.getQuestionsByCategory(category);
        try {
            return new ResponseEntity<>(questionDAO.getQuestionsByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public  ResponseEntity<String> addQuestion(Question question) {
      //  questionDAO.save(question);
     //   return "Record Inserted";
       // return new ResponseEntity<>("SUCCESS",HttpStatus.CREATED);


        try {
            questionDAO.save(question);
            return  new ResponseEntity<>("SUCCESS",HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("FAILURE", HttpStatus.BAD_REQUEST);
    }


    public String updateQuestion(Question question) {
        questionDAO.save(question);
        return "Record Updated";
    }

    public String deleteQuestion(int id) {
        questionDAO.deleteById(id);
        return "Record Deleted";
    }

}
