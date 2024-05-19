package huce.k65.mht1.exam_service.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleEnum {
    ROLE_ADMIN(1),
    ROLE_USER(0);

    private final Integer id;
}
