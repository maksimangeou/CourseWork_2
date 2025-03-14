package pro.sky.java.course2.ExaminerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.ExaminerService.domain.Question;
import pro.sky.java.course2.ExaminerService.exception.NoSuchQuestionException;
import pro.sky.java.course2.ExaminerService.repository.QuestionRepository;
import pro.sky.java.course2.ExaminerService.service.JavaQuestionService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private JavaQuestionService javaQuestionService;

    private Set<Question> questions;

    @BeforeEach
    void setUp() {
        questions = new HashSet<>();
        questions.add(new Question("Q1", "A1"));
        questions.add(new Question("Q2", "A2"));
    }

    @Test
    void givenQuestion_whenAddOne_thenReturnIt() {
        Question newQuestion = new Question("Q3", "A3");

        when(questionRepository.add(newQuestion)).thenReturn(newQuestion);

        Question result = javaQuestionService.add(newQuestion);

        assertEquals(newQuestion, result);
        verify(questionRepository, times(1)).add(newQuestion);
    }

    @Test
    void givenQuestion_whenRemove_thenReturnIt() {
        Question questionToRemove = new Question("Q1", "A1");

        when(questionRepository.remove(questionToRemove)).thenReturn(questionToRemove);

        Question result = javaQuestionService.remove(questionToRemove);

        assertEquals(questionToRemove, result);
        verify(questionRepository, times(1)).remove(questionToRemove);
    }

    @Test
    void givenQuestions_whenTakeThemAll_thenReturnThem() {
        when(questionRepository.getAll()).thenReturn(questions);

        Collection<Question> result = javaQuestionService.getAll();

        assertEquals(questions, result);
        verify(questionRepository, times(1)).getAll();
    }

    @Test
    void givenAll_WhenNoQuestionsAvailable_ShouldThrowNoSuchQuestionException() {
        when(questionRepository.getAll()).thenReturn(new HashSet<>());

        assertThrows(NoSuchQuestionException.class, () -> javaQuestionService.getAll());
        verify(questionRepository, times(1)).getAll();
    }

    @Test
    void givenRandomQuestion_thenReturnRandomQuestion() {
        when(questionRepository.getAll()).thenReturn(questions);

        Question result = javaQuestionService.getRandomQuestion();

        assertTrue(questions.contains(result));
        verify(questionRepository, times(1)).getAll();
    }

    @Test
    void getRandomQuestion_WhenNoQuestionsAvailable_thenThrowNoSuchQuestionException() {
        when(questionRepository.getAll()).thenReturn(new HashSet<>());

        assertThrows(NoSuchQuestionException.class, () -> javaQuestionService.getRandomQuestion());
        verify(questionRepository, times(1)).getAll();
    }
}
