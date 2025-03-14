package pro.sky.java.course2.ExaminerService.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.ExaminerService.domain.Question;
import pro.sky.java.course2.ExaminerService.exception.NoSuchQuestionException;
import pro.sky.java.course2.ExaminerService.repository.QuestionRepository;

import java.util.*;

@Service("javaQuestionService")
public class JavaQuestionService implements QuestionService{

    private final QuestionRepository questionRepository;
    private final Random random = new Random();

    public JavaQuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question,answer);
        questionRepository.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question add(Question question) {
        questionRepository.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        questionRepository.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        Collection<Question> questions = questionRepository.getAll();
        if (questions.isEmpty()) {
            throw new NoSuchQuestionException();
        }
        return questions;
    }

    public Question getRandomQuestion() {
        Collection<Question> questions = questionRepository.getAll();
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
