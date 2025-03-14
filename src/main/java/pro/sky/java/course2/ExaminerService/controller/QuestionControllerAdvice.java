package pro.sky.java.course2.ExaminerService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.java.course2.ExaminerService.exception.MuchQualityQuestionException;
import pro.sky.java.course2.ExaminerService.exception.NoSuchQuestionException;
import pro.sky.java.course2.ExaminerService.exception.RequestError;

@RestControllerAdvice
public class QuestionControllerAdvice {

    @ExceptionHandler(MuchQualityQuestionException.class)
    public ResponseEntity<RequestError> catchMuchQualityQuestionException(MuchQualityQuestionException e) {
        return new ResponseEntity<>(
                new RequestError(Integer.toString(HttpStatus.BAD_REQUEST.value()),
                        e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchQuestionException.class)
    public ResponseEntity<RequestError> catchNoSuchQuestionException(NoSuchQuestionException e) {
        return new ResponseEntity<>(
                new RequestError(Integer.toString(HttpStatus.BAD_REQUEST.value()),
                        e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<RequestError> catchNResponseStatusException(ResponseStatusException e) {
        return new ResponseEntity<>(
                new RequestError(Integer.toString(HttpStatus.METHOD_NOT_ALLOWED.value()),
                        e.getMessage()),
                HttpStatus.METHOD_NOT_ALLOWED);
    }
}
