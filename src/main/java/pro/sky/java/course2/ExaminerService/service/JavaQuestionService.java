package pro.sky.java.course2.ExaminerService.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.ExaminerService.domain.Question;
import pro.sky.java.course2.ExaminerService.exception.NoSuchQuestionException;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService{

    private final Set<Question> questions =new HashSet<>();
    private final Random random = new Random();

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question,answer);
        questions.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        if (questions.isEmpty()) {
            throw new NoSuchQuestionException();
        }
        return questions;
    }

    @Override
    public Question getRandomQuestion() {
        int index = random.nextInt(questions.size());
        Iterator<Question> iterator = questions.iterator();
        for (int i = 0; i < index; i++) {
            iterator.next();
        }
        return iterator.next();
    }
}
