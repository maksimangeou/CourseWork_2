package pro.sky.java.course2.ExaminerService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pro.sky.java.course2.ExaminerService.exception.MuchQualityQuestionException;
import pro.sky.java.course2.ExaminerService.exception.RequestError;

@RestControllerAdvice
public class QuestionControllerAdvice {

    @ExceptionHandler(MuchQualityQuestionException.class)
    public ResponseEntity<RequestError> catchNoSuchProductException(MuchQualityQuestionException e) {
        return new ResponseEntity<>(
                new RequestError(Integer.toString(HttpStatus.BAD_REQUEST.value()),
                        e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
