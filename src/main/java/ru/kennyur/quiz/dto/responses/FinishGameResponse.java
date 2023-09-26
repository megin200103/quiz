package ru.kennyur.quiz.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kennyur.quiz.dto.QuestionResult;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FinishGameResponse {
    private List<QuestionResult> results;
}
