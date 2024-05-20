package huce.k65.mht1.exam_service.repo;

import huce.k65.mht1.exam_service.entity.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamResultRepo extends JpaRepository<ExamResult, Long>, QuerydslPredicateExecutor<ExamResult> {
}