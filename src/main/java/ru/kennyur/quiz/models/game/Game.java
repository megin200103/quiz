package ru.kennyur.quiz.models.game;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@ToString(exclude = {"questions"})
@EqualsAndHashCode(exclude = {"questions"})
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_ended")
    private Boolean isEnded;

    @OrderBy("id")
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<GameQuestion> questions;

    public void addGameQuestion(GameQuestion gameQuestion) {
        if (questions == null) {
            questions = new ArrayList<>();
        }
        questions.add(gameQuestion);
        gameQuestion.setGame(this);
    }
}
