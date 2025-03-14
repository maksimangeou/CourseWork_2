package pro.sky.java.course2.ExaminerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.ExaminerService.domain.Question;
import pro.sky.java.course2.ExaminerService.exception.MuchQualityQuestionException;
import pro.sky.java.course2.ExaminerService.exception.NoSuchQuestionException;
import pro.sky.java.course2.ExaminerService.service.ExaminerServiceImpl;
import pro.sky.java.course2.ExaminerService.service.JavaQuestionService;
import pro.sky.java.course2.ExaminerService.service.MathQuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private JavaQuestionService javaQuestionService;

    @Mock
    private MathQuestionService mathQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private Set<Question> javaQuestions;
    private Set<Question> mathQuestions;

    @BeforeEach
    void setUp() {
        javaQuestions = new HashSet<>();
        javaQuestions.add(new Question("Q1", "A1"));
        javaQuestions.add(new Question("Q2", "A2"));

        mathQuestions = new HashSet<>();
        mathQuestions.add(new Question("2 + 2", "4"));
        mathQuestions.add(new Question("3 * 3", "9"));
    }

    @Test
    void givenQuestions_whenCallGetQuestion_thenReturnCorrectAmountOfQuestions() {
        when(javaQuestionService.getAll()).thenReturn(javaQuestions);
        when(mathQuestionService.getAll()).thenReturn(mathQuestions);

        Collection<Question> questions = examinerService.getQuestions(3);

        assertEquals(3, questions.size());
    }

    @Test
    void givenQuestions_WhenRequestedAmountExceedsAvailableQuestions_thenThrowMuchQualityQuestionException() {
        when(javaQuestionService.getAll()).thenReturn(javaQuestions);
        when(mathQuestionService.getAll()).thenReturn(mathQuestions);

        assertThrows(MuchQualityQuestionException.class, () -> examinerService.getQuestions(5));
    }

    @Test
    void givenQuestions_whenGetQuestion_thenReturnUniqueQuestions() {
        // Мокируем поведение сервисов
        when(javaQuestionService.getAll()).thenReturn(javaQuestions);
        when(mathQuestionService.getAll()).thenReturn(mathQuestions);

        // Запрашиваем 4 вопроса
        Collection<Question> questions = examinerService.getQuestions(4);

        // Проверяем, что все вопросы уникальны
        assertEquals(4, questions.size());
        assertEquals(4, new HashSet<>(questions).size()); // Проверка уникальности
    }

    @Test
    void givenQuestions_whenTakeSome_thenReturnQuestionsFromBothServices() {
        when(javaQuestionService.getAll()).thenReturn(javaQuestions);
        when(mathQuestionService.getAll()).thenReturn(mathQuestions);

        Collection<Question> questions = examinerService.getQuestions(4);

        assertTrue(questions.containsAll(javaQuestions));
        assertTrue(questions.containsAll(mathQuestions));
    }

    @Test
    void givenRandomQuestionFromAll_WhenNoQuestionsAvailable_ShouldThrowNoSuchQuestionException() {
        Set<Question> emptyQuestions = new HashSet<>();

        assertThrows(NoSuchQuestionException.class, () -> examinerService.getRandomQuestionFromAll(emptyQuestions));
    }
}
