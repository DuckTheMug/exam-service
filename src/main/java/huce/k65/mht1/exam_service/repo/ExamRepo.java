package huce.k65.mht1.exam_service.repo;

import huce.k65.mht1.exam_service.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
public interface ExamRepo extends JpaRepository<Exam, Long>, QuerydslPredicateExecutor<Exam> {
    @Modifying
    @Query("update Exam e set e.examName = ?1, e.examDuration = ?2, e.note = ?3, e.accessGroups = ?4, e.parts = ?5, e.creator = ?6, e.examResult = ?7, e.repeatableLimit = ?8, e.repeatableCount = ?9, e.reviewableAfterFinishFlag = ?10")
    int update(String examName, Duration examDuration, String note, AccessGroup accessGroups, Part parts, User creator, ExamResult examResult, Integer repeatableLimit, Integer repeatableCount, Boolean reviewableAfterFinishFlag);
}