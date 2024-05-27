package huce.k65.mht1.exam_service.dto;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;

/**
 * DTO for {@link huce.k65.mht1.exam_service.entity.Exam}
 */
public record ExamAddDto(String examName, Duration examDuration, String note, List<Long> accessGroupAccessGroupIds,
                         List<Long> partPartIds, Integer repeatableLimit, Integer repeatableCount,
                         Boolean reviewableAfterFinishFlag) implements Serializable {
}