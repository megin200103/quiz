package ru.kennyur.quiz.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kennyur.quiz.dto.responses.QuestionResponse;
import ru.kennyur.quiz.exceptions.NotFoundException;
import ru.kennyur.quiz.models.Question;
import ru.kennyur.quiz.repositories.QuestionRepository;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Question with id " + id + " not found"));
    }

    @Override
    public QuestionResponse getRandomQuestion() {
        List<Question> questions = questionRepository.findAll();

        if (questions.isEmpty()) {
            throw new NotFoundException("There are no questions in database");
        }

        Question randomQuestion = questions.get(new Random().nextInt(questions.size()));

        return QuestionResponse.from(randomQuestion);
    }
}
