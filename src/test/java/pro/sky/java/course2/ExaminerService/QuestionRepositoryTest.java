package pro.sky.java.course2.ExaminerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.course2.ExaminerService.domain.Question;
import pro.sky.java.course2.ExaminerService.exception.NoSuchQuestionException;
import pro.sky.java.course2.ExaminerService.repository.QuestionRepository;
import pro.sky.java.course2.ExaminerService.repository.QuestionRepositoryImpl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionRepositoryTest {
    private QuestionRepository questionRepository;
    private Set<Question> testQuestions;

    @BeforeEach
    void setUp() {
        questionRepository = new QuestionRepositoryImpl();
        testQuestions = new HashSet<>();
        testQuestions.add(new Question("Q1", "A1"));
        testQuestions.add(new Question("Q2", "A2"));
    }

    @Test
    void givenQuestion_whenAddOne_thenReturnIt() {
        // Подготовка данных
        Question newQuestion = new Question("Q3", "A3");

        // Вызов метода
        Question result = questionRepository.add(newQuestion);

        // Проверка результата
        assertEquals(newQuestion, result);
        assertTrue(questionRepository.getAll().contains(newQuestion));
    }

    @Test
    void givenQuestion_whenRemove_thenReturnIt() {
        // Подготовка данных
        Question questionToRemove = new Question("Q1", "A1");
        Question anotherQuestion = new Question("Q2", "A2"); // Добавляем еще один вопрос

        // Добавляем вопросы в репозиторий
        questionRepository.add(questionToRemove);
        questionRepository.add(anotherQuestion);

        // Вызов метода
        Question result = questionRepository.remove(questionToRemove);

        // Проверка результата
        assertEquals(questionToRemove, result);

        // Проверяем, что вопрос удален, но коллекция не пуста
        Collection<Question> remainingQuestions = questionRepository.getAll();
        assertFalse(remainingQuestions.contains(questionToRemove));
        assertTrue(remainingQuestions.contains(anotherQuestion));
    }

    @Test
    void givenQuestions_whenTakeThemAll_thenReturnThem() {
        // Добавляем тестовые вопросы
        for (Question question : testQuestions) {
            questionRepository.add(question);
        }

        // Вызов метода
        Collection<Question> result = questionRepository.getAll();

        // Проверка результата
        assertEquals(testQuestions.size(), result.size());
        assertTrue(result.containsAll(testQuestions));
    }

    @Test
    void getRandomQuestion_WhenNoQuestionsAvailable_thenThrowNoSuchQuestionException() {
        // Проверка исключения
        assertThrows(NoSuchQuestionException.class, () -> questionRepository.getAll());
    }
}
