package ru.kennyur.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kennyur.quiz.models.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
