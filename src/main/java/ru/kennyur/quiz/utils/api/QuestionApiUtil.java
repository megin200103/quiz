package ru.kennyur.quiz.utils.api;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import ru.kennyur.quiz.exceptions.NotFoundException;
import ru.kennyur.quiz.models.Question;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class QuestionApiUtil {
    private static final String QUESTION_BY_ID_URL_TEMPLATE = "https://jservice.io/api/clues?offset=";
    private static final String RANDOM_QUESTION_URL = "https://jservice.io/api/random?limit=1";
    private final QuestionApiParser parser;

    @Cacheable(value = "questions", key = "#id")
    public Question getQuestionById(Long id) {
        List<Question> questions = parser.parse(getApiResponseJson(QUESTION_BY_ID_URL_TEMPLATE + (id - 1)));

        System.out.println(questions);

        for (Question question : questions) {
            if (Objects.equals(question.getId(), id)) {
                return question;
            }
        }

        throw new NotFoundException("Question with id " + id + " not found");
    }


    public Question getRandomQuestion() {
        List<Question> questions = parser.parse(getApiResponseJson(RANDOM_QUESTION_URL));
        return questions.get(0);
    }

    public String getApiResponseJson(String requestUrl) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(requestUrl))
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            return null;
        }
    }

}
