package ru.kennyur.quiz.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kennyur.quiz.dto.QuestionResult;
import ru.kennyur.quiz.dto.requests.CreateGameRequest;
import ru.kennyur.quiz.dto.requests.GameCheckAnswerRequest;
import ru.kennyur.quiz.dto.responses.*;
import ru.kennyur.quiz.exceptions.NotFoundException;
import ru.kennyur.quiz.exceptions.QuestionAlreadyAnsweredException;
import ru.kennyur.quiz.models.Question;
import ru.kennyur.quiz.models.game.Game;
import ru.kennyur.quiz.models.game.GameQuestion;
import ru.kennyur.quiz.services.GameService;
import ru.kennyur.quiz.utils.api.QuestionApiUtil;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    private final QuestionApiUtil questionApiUtil;

    @PostMapping
    public ResponseEntity<CreateGameResponse> createGame(@RequestBody CreateGameRequest gameRequest) {
        Game game = Game.builder()
                .isEnded(false)
                .build();
        List<GameQuestion> questions = gameService.generateQuestions(gameRequest.getQuestionNumber(),
                gameRequest.getMinDifficulty(),
                gameRequest.getMaxDifficulty());

        for (GameQuestion question : questions) {
            game.addGameQuestion(question);
        }
        gameService.createGame(game);
        return ResponseEntity.ok(CreateGameResponse.builder()
                .gameId(game.getId())
                .questionsNumber(gameRequest.getQuestionNumber())
                .build());
    }

    @GetMapping("/{gameId}/{questionNumber}")
    public ResponseEntity<GameQuestionResponse> getQuestion(@PathVariable Long gameId, @PathVariable Integer questionNumber) {
        Game game = gameService.getGameById(gameId);
        if (questionNumber > game.getQuestions().size()) {
            throw new NotFoundException("This question not found");
        }

        Question question = questionApiUtil.getQuestionById(game.getQuestions().get(questionNumber - 1).getQuestionId());

        return ResponseEntity.ok(GameQuestionResponse.builder()
                .questionId(questionNumber)
                .questionText(question.getQuestionText())
                .difficulty(question.getDifficulty())
                .build());
    }

    @PostMapping("/{gameId}/{questionNumber}/check")
    public ResponseEntity<GameCheckAnswerResponse> checkAnswer(@PathVariable Long gameId, @PathVariable Integer questionNumber, @RequestBody GameCheckAnswerRequest request) {
        Game game = gameService.getGameById(gameId);
        if (questionNumber > game.getQuestions().size()) {
            throw new NotFoundException("This question not found");
        }

        if (game.getQuestions().get(questionNumber - 1).getIsCorrect() != null) {
            throw new QuestionAlreadyAnsweredException("This question is already answered");
        }

        Question question = questionApiUtil.getQuestionById(game.getQuestions().get(questionNumber - 1).getQuestionId());

        GameCheckAnswerResponse response = GameCheckAnswerResponse.builder()
                .isCorrect(request.getAnswer().equals(question.getAnswer()))
                .correctAnswer(question.getAnswer())
                .build();

        game.getQuestions().get(questionNumber - 1).setIsCorrect(request.getAnswer().equals(question.getAnswer()));

        gameService.updateGame(game);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{gameId}/finish")
    public ResponseEntity<FinishGameResponse> finishGame(@PathVariable Long gameId) {
        Game game = gameService.getGameById(gameId);
        List<QuestionResult> results = new ArrayList<>();
        for (int i = 0; i < game.getQuestions().size(); i++) {
            results.add(QuestionResult.builder()
                    .questionNumber(i + 1)
                    .isCorrect(game.getQuestions().get(i).getIsCorrect() != null && game.getQuestions().get(i).getIsCorrect())
                    .build());
        }

        game.setIsEnded(true);
        gameService.updateGame(game);

        return ResponseEntity.ok(FinishGameResponse.builder()
                .results(results)
                .build());
    }

}
