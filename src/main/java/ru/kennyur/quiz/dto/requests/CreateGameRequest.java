package ru.kennyur.quiz.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateGameRequest {

    @JsonProperty("question_number")
    private Integer questionNumber;

    @JsonProperty("min_difficulty")
    private Integer minDifficulty;

    @JsonProperty("max_difficulty")
    private Integer maxDifficulty;
}
