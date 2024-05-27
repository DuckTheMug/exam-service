package huce.k65.mht1.exam_service.constant;

import com.querydsl.core.types.Predicate;
import huce.k65.mht1.exam_service.entity.QExam;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DeletedPredicateEnum {
    EXAM(QExam.exam.deleted_flag.eq(false)
            .and(QExam.exam.accessGroups.any().deletedFlag.eq(false))
            .and(QExam.exam.creator.deletedFlag.eq(false))
            .and(QExam.exam.examResult.any().deletedFlag.eq(false))
            .and(QExam.exam.parts.any().deletedFlag.eq(false)));

    private final Predicate predicate;
}
