package ru.kennyur.quiz.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_text")
    private String questionText;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private Integer difficulty;

    private String answer;

    @JsonProperty("question")
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    @JsonProperty("value")
    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }
}
