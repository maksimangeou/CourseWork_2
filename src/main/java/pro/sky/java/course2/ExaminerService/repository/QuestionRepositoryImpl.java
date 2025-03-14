package pro.sky.java.course2.ExaminerService.repository;

import pro.sky.java.course2.ExaminerService.domain.Question;
import pro.sky.java.course2.ExaminerService.exception.NoSuchQuestionException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class QuestionRepositoryImpl implements QuestionRepository {
    private final Set<Question> questions = new HashSet<>();

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
}
