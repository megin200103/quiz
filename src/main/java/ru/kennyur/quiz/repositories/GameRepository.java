package ru.kennyur.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kennyur.quiz.models.game.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
