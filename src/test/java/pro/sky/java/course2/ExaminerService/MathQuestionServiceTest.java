package pro.sky.java.course2.ExaminerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.java.course2.ExaminerService.domain.Question;
import pro.sky.java.course2.ExaminerService.exception.NoSuchQuestionException;
import pro.sky.java.course2.ExaminerService.repository.QuestionRepository;
import pro.sky.java.course2.ExaminerService.service.JavaQuestionService;
import pro.sky.java.course2.ExaminerService.service.MathQuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MathQuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private MathQuestionService mathQuestionService;

    private Set<Question> questions;

    @BeforeEach
    void setUp() {
        questions = new HashSet<>();
        questions.add(new Question("Q1", "A1"));
        questions.add(new Question("Q2", "A2"));
    }

    @Test
    void givenQuestion_whenAddOne_thenThrowMethodNotAllowed() {
        Question question = new Question("Q1", "A1");
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> mathQuestionService.add(question));
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, exception.getStatusCode());
    }

    @Test
    void givenQuestion_whenRemove_thenThrowMethodNotAllowed() {
        Question question = new Question("Q1", "A1");
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> mathQuestionService.remove(question));
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, exception.getStatusCode());
    }

    @Test
    void givenQuestions_whenTakeThemAll_thenThrowMethodNotAllowed() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> mathQuestionService.getAll());
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, exception.getStatusCode());
    }

    @Test
    void givenAll_WhenNoQuestionsAvailable_ShouldThrowMethodNotAllowed() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> mathQuestionService.getAll());
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, exception.getStatusCode());
    }

    @Test
    void givenRandomQuestion_thenReturnRandomQuestion() {
        Question question = mathQuestionService.getRandomQuestion();
        assertNotNull(question);
        assertTrue(question.getQuestion().contains(" + "));
        assertTrue(question.getAnswer().matches("\\d+"));
    }
}
