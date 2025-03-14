package pro.sky.java.course2.ExaminerService.exception;

public class NoSuchQuestionException extends RuntimeException {
    public NoSuchQuestionException() {
        super("Хранилище вопросов не заполнено");
    }
}
