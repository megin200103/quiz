package ru.kennyur.quiz.utils.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kennyur.quiz.models.Question;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class QuestionApiParser {
    private final ObjectMapper objectMapper;

    public List<Question> parse(String json) {
        try {
            List<Question> questions = new ArrayList<>();

            JsonNode res = objectMapper.readTree(json);

            if (!res.isEmpty()) {
                for (JsonNode object : res) {
                    questions.add(objectMapper.treeToValue(object, Question.class));
                }
            }

            return questions;
        } catch (JsonProcessingException ignored) {
        }

        return new ArrayList<>();
    }
}
