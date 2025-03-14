package pro.sky.java.course2.ExaminerService.exception;

import java.util.UUID;

public class NoSuchQuestionException extends RuntimeException {
    public NoSuchQuestionException() {
        super("Хранилище вопросов не заполнено");
    }
}
