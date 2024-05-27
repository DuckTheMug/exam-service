package huce.k65.mht1.exam_service.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.List;

/**
 * DTO for {@link huce.k65.mht1.exam_service.entity.Exam}
 */
public record ExamSearchResponseDto(Long examId, String examName, Duration examDuration, String note,
                                    List<AccessGroupDto> accessGroups, List<PartDto> parts, UserDto creator,
                                    List<ExamResultDto> examResult, Integer repeatableLimit, Integer repeatableCount,
                                    Boolean reviewableAfterFinishFlag) implements Serializable {
    /**
     * DTO for {@link huce.k65.mht1.exam_service.entity.AccessGroup}
     */
    public record AccessGroupDto(Long accessGroupId, String accessGroupName, Timestamp accessStartDateAndTime,
                                 Timestamp accessEndDateAndTime) implements Serializable {
    }

    /**
     * DTO for {@link huce.k65.mht1.exam_service.entity.Part}
     */
    public record PartDto(Long partId, String partName) implements Serializable {
    }

    /**
     * DTO for {@link huce.k65.mht1.exam_service.entity.User}
     */
    public record UserDto(Long userId, String fullName) implements Serializable {
    }

    /**
     * DTO for {@link huce.k65.mht1.exam_service.entity.ExamResult}
     */
    public record ExamResultDto(Long examResultId, Float totalScore) implements Serializable {
    }
}