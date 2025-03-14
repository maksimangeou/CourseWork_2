package pro.sky.java.course2.ExaminerService.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.java.course2.ExaminerService.domain.Question;
import pro.sky.java.course2.ExaminerService.exception.NoSuchQuestionException;

import java.util.*;

@Service("mathQuestionService")
public class MathQuestionService implements QuestionService {

    private final Random random = new Random();

    @Override
    public Question add(String question, String answer) {
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Добавление вопросов по математике невозможно");
    }

    @Override
    public Question add(Question question) {
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Добавление вопросов по математике невозможно");
    }

    @Override
    public Question remove(Question question) {
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Удаление вопросов по математике невозможно");
    }

    @Override
    public Collection<Question> getAll() {
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Вопросы по математике не доступны");
    }

    @Override
    public Question getRandomQuestion() {
        int a = random.nextInt(100);
        int b = random.nextInt(100);
        String question = a + " + " + b + " = ?";
        String answer = String.valueOf(a + b);
        return new Question(question, answer);
    }
}
