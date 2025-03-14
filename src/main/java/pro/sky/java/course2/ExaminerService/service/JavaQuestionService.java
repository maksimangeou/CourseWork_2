package pro.sky.java.course2.ExaminerService.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.ExaminerService.domain.Question;
import pro.sky.java.course2.ExaminerService.exception.NoSuchQuestionException;
import pro.sky.java.course2.ExaminerService.repository.JavaQuestionRepository;

import java.util.*;

@Service("javaQuestionService")
public class JavaQuestionService implements QuestionService {

    private final JavaQuestionRepository javaQuestionRepository;
    private final Random random = new Random();

    public JavaQuestionService(JavaQuestionRepository javaQuestionRepository) {
        this.javaQuestionRepository = javaQuestionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        javaQuestionRepository.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question add(Question question) {
        javaQuestionRepository.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        javaQuestionRepository.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        Collection<Question> questions = javaQuestionRepository.getAll();
        if (questions.isEmpty()) {
            throw new NoSuchQuestionException();
        }
        return questions;
    }

    public Question getRandomQuestion() {
        Collection<Question> questions = javaQuestionRepository.getAll();
        if (questions.isEmpty()) {
            throw new NoSuchQuestionException();
        }
        int index = random.nextInt(questions.size());

        Iterator<Question> iterator = questions.iterator();
        Question result = null;

        for (int i = 0; i <= index; i++) {
            if (iterator.hasNext()) {
                result = iterator.next();
            } else {
                throw new NoSuchQuestionException();
            }
        }

        return result;
    }
}
