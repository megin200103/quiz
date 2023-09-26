package ru.kennyur.quiz.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.kennyur.quiz.dto.responses.ExceptionResponse;
import ru.kennyur.quiz.exceptions.GameFinishedException;
import ru.kennyur.quiz.exceptions.NotFoundException;
import ru.kennyur.quiz.exceptions.QuestionAlreadyAnsweredException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(GameFinishedException.class)
    public ResponseEntity<ExceptionResponse> handleGameFinishedException(GameFinishedException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponse.builder()
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(QuestionAlreadyAnsweredException.class)
    public ResponseEntity<ExceptionResponse> handleQuestionAlreadyAnsweredException(QuestionAlreadyAnsweredException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponse.builder()
                        .message(e.getMessage())
                        .build());
    }
}
