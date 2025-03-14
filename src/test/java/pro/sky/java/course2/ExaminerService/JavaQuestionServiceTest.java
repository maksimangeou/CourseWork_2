package pro.sky.java.course2.ExaminerService;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import pro.sky.java.course2.ExaminerService.domain.Question;
import pro.sky.java.course2.ExaminerService.service.JavaQuestionService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class JavaQuestionServiceTest {

    @Mock
    JavaQuestionService javaQuestionService;

    @Test
    void givenQuestionAndAnswer_whenAddInCollection_thenSuccess() {
        JavaQuestionService javaQuestionService = new JavaQuestionService();
        javaQuestionService.add("Q1", "A1");
        javaQuestionService.add("Q2", "A2");

        Collection<Question> questions = javaQuestionService.getAll();
        assertEquals(2, questions.size());
    }

    @Test
    void givenQuestion_whenAddInCollection_thenSuccess() {
        Question question1 = new Question("Q1", "A1");
        Question question2 = new Question("Q2", "A2");
        JavaQuestionService javaQuestionService = new JavaQuestionService();
        javaQuestionService.add(question1);
        javaQuestionService.add(question2);

        Collection<Question> questions = javaQuestionService.getAll();
        assertEquals(2, questions.size());
    }

    @Test
    void givenQuestion_whenRemoveFromCollection_thenSuccess() {
        JavaQuestionService service = new JavaQuestionService();
        Question question = service.add("Q1", "A1");
        service.remove(question);

        Collection<Question> questions = service.getAll();
        assertEquals(0, questions.size());
    }

    @Test
    void getRandomQuestionWhenNoQuestions() {
        assertThrows(IllegalArgumentException.class, () -> javaQuestionService.getRandomQuestion());
    }

    @Test
    void getRandomQuestionWhenOneQuestion() {
        Question question = new Question("What is Java?", "A programming language");
        javaQuestionService.add(question);

        Question randomQuestion = javaQuestionService.getRandomQuestion();

        assertEquals(question, randomQuestion);
    }

    @Test
    void getRandomQuestionWhenMultipleQuestions() {
        Set<Question> questions = new HashSet<>();
        questions.add(new Question("What is Java?", "A programming language"));
        questions.add(new Question("What is Spring?", "A framework"));
        questions.add(new Question("What is Maven?", "A build tool"));

        for (Question question : questions) {
            javaQuestionService.add(question);
        }

        Question randomQuestion = javaQuestionService.getRandomQuestion();

        assertTrue(questions.contains(randomQuestion));
    }

    @Test
    void getRandomQuestionDistribution() {
        Set<Question> questions = new HashSet<>();
        questions.add(new Question("What is Java?", "A programming language"));
        questions.add(new Question("What is Spring?", "A framework"));
        questions.add(new Question("What is Maven?", "A build tool"));

        for (Question question : questions) {
            javaQuestionService.add(question);
        }

        int numberOfTests = 1000;
        Map<Question, Integer> questionCount = new HashMap<>();

        for (int i = 0; i < numberOfTests; i++) {
            Question randomQuestion = javaQuestionService.getRandomQuestion();
            questionCount.put(randomQuestion, questionCount.getOrDefault(randomQuestion, 0) + 1);
        }

        for (Question question : questions) {
            assertTrue(questionCount.containsKey(question));
            assertTrue(questionCount.get(question) > 0);
        }
    }
}
