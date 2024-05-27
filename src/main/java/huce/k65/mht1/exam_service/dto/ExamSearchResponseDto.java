package huce.k65.mht1.exam_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;

/**
 * DTO for {@link huce.k65.mht1.exam_service.entity.Exam}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ExamSearchResponseDto implements Serializable {
    private Long examId;
    private String examName;
    private Duration examDuration;
    private String note;
    private List<AccessGroupDto> accessGroups;
    private UserDto creator;
    private Integer repeatableLimit;
    private Integer repeatableCount;
    private Boolean reviewableAfterFinishFlag = false;

    /**
     * DTO for {@link huce.k65.mht1.exam_service.entity.AccessGroup}
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class AccessGroupDto implements Serializable {
        private String accessGroupName;
    }

    /**
     * DTO for {@link huce.k65.mht1.exam_service.entity.User}
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class UserDto implements Serializable {
        private String fullName;
    }
}