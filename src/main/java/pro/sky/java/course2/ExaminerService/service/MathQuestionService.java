package pro.sky.java.course2.ExaminerService.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.ExaminerService.domain.Question;
import pro.sky.java.course2.ExaminerService.exception.NoSuchQuestionException;
import pro.sky.java.course2.ExaminerService.repository.MathQuestionRepository;

import java.util.*;

@Service("mathQuestionService")
public class MathQuestionService implements QuestionService {

    private final MathQuestionRepository mathQuestionRepository;
    private final Random random = new Random();

    public MathQuestionService(MathQuestionRepository mathQuestionRepository) {
        this.mathQuestionRepository = mathQuestionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        mathQuestionRepository.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question add(Question question) {
        mathQuestionRepository.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        mathQuestionRepository.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        Collection<Question> questions = mathQuestionRepository.getAll();
        if (questions.isEmpty()) {
            throw new NoSuchQuestionException();
        }
        return questions;
    }

    @Override
    public Question getRandomQuestion() {
        Collection<Question> questions = mathQuestionRepository.getAll();
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
