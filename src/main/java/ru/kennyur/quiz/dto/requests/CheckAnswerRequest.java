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
public class CheckAnswerRequest {

    @JsonProperty("question_id")
    private Long questionId;

    private String answer;
}
