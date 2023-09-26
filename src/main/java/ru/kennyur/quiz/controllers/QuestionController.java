package ru.kennyur.quiz.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kennyur.quiz.dto.requests.CheckAnswerRequest;
import ru.kennyur.quiz.dto.responses.CheckAnswerResponse;
import ru.kennyur.quiz.dto.responses.QuestionResponse;
import ru.kennyur.quiz.models.Question;
import ru.kennyur.quiz.services.QuestionService;
import ru.kennyur.quiz.utils.api.QuestionApiUtil;

@RequiredArgsConstructor
@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    private final QuestionApiUtil questionApiUtil;

    @GetMapping("/random")
    public ResponseEntity<QuestionResponse> getRandomQuestion() {
        return ResponseEntity.ok(questionService.getRandomQuestion());
    }

    @PostMapping("/check")
    public ResponseEntity<CheckAnswerResponse> checkAnswer(@RequestBody CheckAnswerRequest request) {
        Question question = questionService.getQuestionById(request.getQuestionId());
        CheckAnswerResponse response = CheckAnswerResponse.builder()
                .questionId(question.getId())
                .isCorrect(request.getAnswer().equals(question.getAnswer()))
                .correctAnswer(question.getAnswer())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/random")
    public ResponseEntity<QuestionResponse> getRandomQuestionFromApi() {
        return ResponseEntity.ok(QuestionResponse.from(questionApiUtil.getRandomQuestion()));
    }

    @GetMapping("/api/{id}")
    public ResponseEntity<QuestionResponse> getQuestionFromApiById(@PathVariable Long id) {
        return ResponseEntity.ok(QuestionResponse.from(questionApiUtil.getQuestionById(id)));
    }

    @PostMapping("/api/check")
    public ResponseEntity<CheckAnswerResponse> checkAnswerFromApi(@RequestBody CheckAnswerRequest request) {
        Question question = questionApiUtil.getQuestionById(request.getQuestionId());
        CheckAnswerResponse response = CheckAnswerResponse.builder()
                .questionId(question.getId())
                .isCorrect(request.getAnswer().equals(question.getAnswer()))
                .correctAnswer(question.getAnswer())
                .build();

        return ResponseEntity.ok(response);
    }
}
