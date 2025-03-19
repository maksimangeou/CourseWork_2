package pro.sky.java.course2.ExaminerService.exception;

public class MuchQualityQuestionException extends RuntimeException {
    public MuchQualityQuestionException() {
        super("Количество вопросов в хранилище меньше, чем запрашивается");
    }
}
