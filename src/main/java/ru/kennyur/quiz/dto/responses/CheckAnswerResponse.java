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
public class CheckAnswerResponse {

    @JsonProperty("question_id")
    private Long questionId;

    @JsonProperty("is_correct")
    private Boolean isCorrect;

    @JsonProperty("correct_answer")
    private String correctAnswer;
}
