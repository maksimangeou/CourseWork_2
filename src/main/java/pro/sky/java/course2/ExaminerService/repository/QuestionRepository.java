package pro.sky.java.course2.ExaminerService.repository;

import pro.sky.java.course2.ExaminerService.domain.Question;

import java.util.Collection;

public interface QuestionRepository {
    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();
}

