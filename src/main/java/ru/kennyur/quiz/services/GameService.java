package ru.kennyur.quiz.services;

import ru.kennyur.quiz.models.game.Game;
import ru.kennyur.quiz.models.game.GameQuestion;

import java.util.List;

public interface GameService {
    void createGame(Game game);
    List<GameQuestion> generateQuestions(int questionsNumber, int minDifficulty, int maxDifficulty);

    Game getGameById(Long id);

    void updateGame(Game game);
}
