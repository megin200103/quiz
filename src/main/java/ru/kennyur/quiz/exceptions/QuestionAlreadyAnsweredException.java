package ru.kennyur.quiz.exceptions;

public class QuestionAlreadyAnsweredException extends RuntimeException {
    public QuestionAlreadyAnsweredException(String message) {
        super(message);
    }
}
