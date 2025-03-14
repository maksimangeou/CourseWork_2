package pro.sky.java.course2.ExaminerService;

import org.junit.jupiter.api.Test;
import pro.sky.java.course2.ExaminerService.domain.Question;
import pro.sky.java.course2.ExaminerService.repository.QuestionRepository;
import pro.sky.java.course2.ExaminerService.repository.QuestionRepositoryImpl;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionRepositoryTest {
    private final QuestionRepository questionRepository = new QuestionRepositoryImpl();

    @Test
    void addAndGetQuestions() {
        Question question = new Question("10 / 2", "5");
        questionRepository.add(question);

        Collection<Question> questions = questionRepository.getAll();
        assertEquals(1, questions.size());
    }

    @Test
    void removeQuestion() {
        Question question = new Question("8 + 2", "10");
        questionRepository.add(question);
        questionRepository.remove(question);

        Collection<Question> questions = questionRepository.getAll();
        assertEquals(0, questions.size());
    }
}
