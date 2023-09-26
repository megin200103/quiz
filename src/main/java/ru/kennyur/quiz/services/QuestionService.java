package ru.kennyur.quiz.services;

import ru.kennyur.quiz.dto.responses.QuestionResponse;
import ru.kennyur.quiz.models.Question;

public interface QuestionService {
    Question getQuestionById(Long id);

    QuestionResponse getRandomQuestion();
}
