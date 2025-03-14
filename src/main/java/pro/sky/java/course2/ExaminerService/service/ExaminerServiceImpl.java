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
    @Autowired
    @Qualifier("javaQuestionService")
    private final JavaQuestionService javaQuestionService;
    @Autowired
    @Qualifier("mathQuestionService")
    private final MathQuestionService mathQuestionService;

    public ExaminerServiceImpl(JavaQuestionService javaQuestionService,
                               MathQuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Set<Question> allQuestion = new HashSet<>();
        allQuestion.addAll(javaQuestionService.getAll());
        allQuestion.addAll(mathQuestionService.getAll());

        if (amount > allQuestion.size()) {
            throw new MuchQualityQuestionException();
        }
        Collection<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(getRandomQuestionFromAll(allQuestion));
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
