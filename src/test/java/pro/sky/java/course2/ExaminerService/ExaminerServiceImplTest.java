package pro.sky.java.course2.ExaminerService;

import org.junit.jupiter.api.Test;
import pro.sky.java.course2.ExaminerService.domain.Question;
import pro.sky.java.course2.ExaminerService.service.ExaminerServiceImpl;
import pro.sky.java.course2.ExaminerService.service.JavaQuestionService;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExaminerServiceImplTest {

    @Test
    void getQuestions() {
        JavaQuestionService questionService = new JavaQuestionService();
        questionService.add("Q1", "A1");
        questionService.add("Q2", "A2");

        ExaminerServiceImpl examinerService = new ExaminerServiceImpl(questionService);
        Collection<Question> questions = examinerService.getQuestions(2);

        assertEquals(2, questions.size());
    }
}
