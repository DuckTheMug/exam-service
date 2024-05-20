package huce.k65.mht1.exam_service.repo;

import huce.k65.mht1.exam_service.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepo extends JpaRepository<Answer, Long>, QuerydslPredicateExecutor<Answer> { }