package pro.sky.java.course2.ExaminerService.service;

import pro.sky.java.course2.ExaminerService.domain.Question;

import java.util.Collection;
import java.util.HashSet;


public class ExaminerServiceImpl implements ExaminerService{
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > questionService.getAll().size()) {
            throw new IllegalArgumentException("Bad Request");
        }
        Collection<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(questionService.getRandomQuestion());
        }
        return questions;
    }
}
