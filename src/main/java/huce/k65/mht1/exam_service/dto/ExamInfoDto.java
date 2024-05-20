package huce.k65.mht1.exam_service.dto;

import huce.k65.mht1.exam_service.entity.AccessGroup;
import huce.k65.mht1.exam_service.entity.ExamResult;
import huce.k65.mht1.exam_service.entity.Part;
import huce.k65.mht1.exam_service.entity.User;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;

/**
 * DTO for {@link huce.k65.mht1.exam_service.entity.Exam}
 */
public record ExamInfoDto(Long examId, String examName, Duration examDuration, String note,
                          List<AccessGroup> accessGroups, List<Part> parts, User creator, List<ExamResult> examResult,
                          Integer repeatableLimit, Integer repeatableCount,
                          Boolean reviewableAfterFinishFlag) implements Serializable {
}