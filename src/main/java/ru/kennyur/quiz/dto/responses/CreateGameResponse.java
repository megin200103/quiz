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
public class CreateGameResponse {

    @JsonProperty("game_id")
    private Long gameId;

    @JsonProperty("questions_number")
    private Integer questionsNumber;
}
