package com.telusko.quizapp.service;

import com.telusko.quizapp.dao.QuestionDAO;
import com.telusko.quizapp.dao.QuizDAO;
import com.telusko.quizapp.model.AnswerByUserReponse;
import com.telusko.quizapp.model.Question;
import com.telusko.quizapp.model.QuestionWrapper;
import com.telusko.quizapp.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDAO quizDAO;

    @Autowired
    QuestionDAO questionDAO;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questionList = questionDAO.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questionList);
        quizDAO.save(quiz);
        return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);


    }

    public ResponseEntity<?> getQuizQuestions(int id) {
        try {
            Optional<Quiz> quiz = quizDAO.findById(id);
            List<Question> questionFromDB = quiz.get().getQuestions();
            List<QuestionWrapper> questionForUsers = new ArrayList<>();
            for (Question q : questionFromDB) {
                QuestionWrapper questionWrapper = new QuestionWrapper(q.getId(), q.getQuestion_title(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
                questionForUsers.add(questionWrapper);
            }
            return new ResponseEntity<>(questionForUsers, HttpStatus.OK);
        } catch (Exception e) {
            //  e.printStackTrace();
            //  return new ResponseEntity<>(HttpStatus.NOT_FOUND).body("No Record");
            //   ResponseEntity responseEntity =  ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Record");
            //   return new ResponseEntity<>( responseEntity, HttpStatus.OK);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Record");
        }
        //return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> calculateResult(int id, List<Question> answersByUserReponse) {
        Optional<Quiz> quiz = quizDAO.findById(id);
        List<Question> questionList = quiz.get().getQuestions();

        System.out.println("id : " + id);
        System.out.println("questionList");
        questionList.forEach(System.out::println);
        System.out.println("============================");
        System.out.println("answersByUserReponse");
        answersByUserReponse.forEach(System.out::println);
        System.out.println("===========================");

        int rightAnsCount = 0;
        int totalQuestions=0;
        int wrongAnswers=0;
        int unAttend=0;

        List<String> resultList = new ArrayList<>();


        for (Question q : questionList) {
            totalQuestions++;
            for (Question a : answersByUserReponse) {
                if ((q.getId() == a.getId()) && ( q.getRight_answer().equals(a.getRight_answer()))){
                    rightAnsCount++;
                }
            }
        }
        wrongAnswers = answersByUserReponse.size()-rightAnsCount;
        unAttend = totalQuestions-answersByUserReponse.size();
        resultList.add("Total Questions : "+totalQuestions);
        resultList.add("Right Answers : "+rightAnsCount);
        resultList.add("Wrong Answers : "+wrongAnswers);
        resultList.add("unAttend : "+unAttend);
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }
}
