package huce.k65.mht1.exam_service.dto;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;

/**
 * DTO for {@link huce.k65.mht1.exam_service.entity.Exam}
 */
public record ExamDto(Long examId, String examName, Duration examDuration, String note, List<AccessGroupDto> accessGroups, List<PartDto> parts, UserDto creator, Integer repeatableLimit, Integer repeatableCount, Boolean reviewableAfterFinishFlag) implements Serializable {
    /**
     * DTO for {@link huce.k65.mht1.exam_service.entity.AccessGroup}
     */
    public record AccessGroupDto(Long accessGroupId) implements Serializable {
    }

    /**
     * DTO for {@link huce.k65.mht1.exam_service.entity.Part}
     */
    public record PartDto(Long partId) implements Serializable {
    }

    /**
     * DTO for {@link huce.k65.mht1.exam_service.entity.User}
     */
    public record UserDto(Long userId) implements Serializable {
    }
}