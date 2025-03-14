package pro.sky.java.course2.ExaminerService;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import pro.sky.java.course2.ExaminerService.domain.Question;
import pro.sky.java.course2.ExaminerService.repository.QuestionRepository;
import pro.sky.java.course2.ExaminerService.service.ExaminerServiceImpl;
import pro.sky.java.course2.ExaminerService.service.JavaQuestionService;
import pro.sky.java.course2.ExaminerService.service.MathQuestionService;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExaminerServiceImplTest {

    @Mock
    private QuestionRepository questionRepository;
    private JavaQuestionService javaQuestionService;
    private MathQuestionService mathQuestionService;

    @Test
    void getQuestions() {
        JavaQuestionService questionService = new JavaQuestionService(questionRepository);
        questionService.add("Q1", "A1");
        questionService.add("Q2", "A2");

        ExaminerServiceImpl examinerService = new ExaminerServiceImpl(javaQuestionService, mathQuestionService);
        Collection<Question> questions = examinerService.getQuestions(2);

        assertEquals(2, questions.size());
    }
}
