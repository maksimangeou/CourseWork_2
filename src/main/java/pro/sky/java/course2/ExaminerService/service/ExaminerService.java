package pro.sky.java.course2.ExaminerService.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.ExaminerService.domain.Question;

import java.util.Collection;

@Service
public interface ExaminerService {

    Collection<Question> getQuestions(int amount);
}
