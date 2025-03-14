package pro.sky.java.course2.ExaminerService.service;

import pro.sky.java.course2.ExaminerService.domain.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions(int amount);
}
