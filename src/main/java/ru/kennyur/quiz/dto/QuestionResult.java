package ru.kennyur.quiz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class QuestionResult {

    @JsonProperty("question_number")
    private Integer questionNumber;

    @JsonProperty("is_correct")
    private Boolean isCorrect;
}
