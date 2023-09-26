package ru.kennyur.quiz.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kennyur.quiz.exceptions.GameFinishedException;
import ru.kennyur.quiz.exceptions.NotFoundException;
import ru.kennyur.quiz.models.Question;
import ru.kennyur.quiz.models.game.Game;
import ru.kennyur.quiz.models.game.GameQuestion;
import ru.kennyur.quiz.repositories.GameRepository;
import ru.kennyur.quiz.utils.api.QuestionApiUtil;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    private final QuestionApiUtil questionApiUtil;

    @Override
    public void createGame(Game game) {
        gameRepository.save(game);
    }

    @Override
    public List<GameQuestion> generateQuestions(int questionsNumber, int minDifficulty, int maxDifficulty) {
        List<GameQuestion> questions = new ArrayList<>();
        int count = 0;

        while (count != questionsNumber) {
            Question question = questionApiUtil.getRandomQuestion();
            if (question.getDifficulty() == null || question.getDifficulty() < maxDifficulty || question.getDifficulty() > maxDifficulty) continue;
            questions.add(GameQuestion.builder()
                    .questionId(question.getId())
                    .build());
            count++;
        }

        return questions;
    }

    @Override
    public Game getGameById(Long id) {
        Game game = gameRepository.findById(id).orElseThrow(() -> new NotFoundException("Game with id " + id + " not found"));
        if (game.getIsEnded()) {
            throw new GameFinishedException("This game is already finished");
        }
        return game;
    }

    @Override
    public void updateGame(Game game) {
        gameRepository.save(game);
    }
}
