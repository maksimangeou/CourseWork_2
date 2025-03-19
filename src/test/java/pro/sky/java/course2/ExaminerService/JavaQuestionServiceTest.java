package pro.sky.java.course2.ExaminerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.ExaminerService.domain.Question;
import pro.sky.java.course2.ExaminerService.repository.JavaQuestionRepository;
import pro.sky.java.course2.ExaminerService.service.JavaQuestionService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {

    @Mock
    private JavaQuestionRepository javaQuestionRepository;

    @InjectMocks
    private JavaQuestionService javaQuestionService;

    private Question question;

    @BeforeEach
    void setUp() {
        question = new Question("Q1", "A1");
    }

    @Test
    void add_ShouldAddQuestionAndReturnIt() {
        when(javaQuestionRepository.add(question)).thenReturn(question);

        Question result = javaQuestionService.add(question);

        assertEquals(question, result);
        verify(javaQuestionRepository, times(1)).add(question);
    }

    @Test
    void remove_ShouldRemoveQuestionAndReturnIt() {
        when(javaQuestionRepository.remove(question)).thenReturn(question);

        Question result = javaQuestionService.remove(question);

        assertEquals(question, result);
        verify(javaQuestionRepository, times(1)).remove(question);
    }

    @Test
    void getAll_ShouldReturnAllQuestions() {
        Collection<Question> questions = List.of(question);
        when(javaQuestionRepository.getAll()).thenReturn(questions);

        Collection<Question> result = javaQuestionService.getAll();

        assertEquals(questions, result);
        verify(javaQuestionRepository, times(1)).getAll();
    }

    @Test
    void getRandomQuestion_ShouldReturnRandomQuestion() {
        Collection<Question> questions = List.of(question);
        when(javaQuestionRepository.getAll()).thenReturn(questions);

        Question result = javaQuestionService.getRandomQuestion();

        assertNotNull(result);
        verify(javaQuestionRepository, times(1)).getAll();
    }
}
