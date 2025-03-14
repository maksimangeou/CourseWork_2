package pro.sky.java.course2.ExaminerService;

import org.junit.jupiter.api.Test;
import pro.sky.java.course2.ExaminerService.domain.Question;
import pro.sky.java.course2.ExaminerService.repository.QuestionRepository;
import pro.sky.java.course2.ExaminerService.repository.QuestionRepositoryImpl;
import pro.sky.java.course2.ExaminerService.service.MathQuestionService;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathQuestionServiceTest {

    private final QuestionRepository questionRepository = new QuestionRepositoryImpl();
    private final MathQuestionService mathQuestionService = new MathQuestionService(questionRepository);

    @Test
    void addAndGetQuestions() {
        mathQuestionService.add("2 + 2", "4");
        mathQuestionService.add("3 * 3", "9");

        Collection<Question> questions = mathQuestionService.getAll();
        assertEquals(2, questions.size());
    }

    @Test
    void removeQuestion() {
        Question question = mathQuestionService.add("5 - 3", "2");
        mathQuestionService.remove(question);

        Collection<Question> questions = mathQuestionService.getAll();
        assertEquals(0, questions.size());
    }
}
