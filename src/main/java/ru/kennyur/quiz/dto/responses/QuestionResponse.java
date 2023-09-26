package ru.kennyur.quiz.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kennyur.quiz.models.Category;
import ru.kennyur.quiz.models.Question;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class QuestionResponse {

    private Long id;

    @JsonProperty("question")
    private String questionText;

    private Category category;

    private Integer difficulty;

    public static QuestionResponse from(Question question) {
        QuestionResponse result = QuestionResponse.builder()
                .id(question.getId())
                .questionText(question.getQuestionText())
                .category(question.getCategory())
                .difficulty(question.getDifficulty())
                .build();

        return result;
    }
}
