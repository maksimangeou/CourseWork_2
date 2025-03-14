package pro.sky.java.course2.ExaminerService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.ExaminerService.exception.MuchQualityQuestionException;
import pro.sky.java.course2.ExaminerService.domain.Question;
import pro.sky.java.course2.ExaminerService.exception.NoSuchQuestionException;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final Set<QuestionService> questionServices;

    public ExaminerServiceImpl(Set<QuestionService> questionServices) {
        this.questionServices = questionServices;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Set<Question> allQuestions = new HashSet<>();
        for (QuestionService service : questionServices) {
            allQuestions.addAll(service.getAll());
        }

        if (amount > allQuestions.size()) {
            throw new MuchQualityQuestionException();
        }

        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(getRandomQuestionFromAll(allQuestions));
        }
        return questions;
    }

    public Question getRandomQuestionFromAll(Collection<Question> allQuestion) {
        if (allQuestion.isEmpty()) {
            throw new NoSuchQuestionException();
        }
        int index = new Random().nextInt(allQuestion.size());
        Iterator<Question> iterator = allQuestion.iterator();
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
