package ru.kennyur.quiz.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GameQuestionResponse {

    @JsonProperty("id")
    private Integer questionId;

    @JsonProperty("question")
    private String questionText;

    private Integer difficulty;
}
