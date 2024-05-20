package huce.k65.mht1.exam_service.entity;

import huce.k65.mht1.exam_service.constant.RoleEnum;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE public.e_user SET deleted_flag = true WHERE public.e_user.user_id = ?")
@FilterDef(name = "userDeletedFlagFilter", parameters = @ParamDef(name = "deletedFlag", type = Boolean.class))
@Filter(name = "userDeletedFlagFilter", condition = "deleted_flag = :deletedFlag")
@Table(name = "e_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "e_user_seq")
    @SequenceGenerator(name = "e_user_seq")
    @Column(name = "user_id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long userId;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "first_name", nullable = false, length = 50)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String firstName;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "last_name", nullable = false, length = 50)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String lastName;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "full_name", nullable = false, length = 100)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String fullName;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "email", nullable = false, length = 100)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String email;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "password", nullable = false, length = 150)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String password;

    @Basic(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 30)
    private RoleEnum role;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = BelongClass.class)
    @JoinColumn(name = "belong_class_id")
    private BelongClass belongClass;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, targetEntity = ExamResult.class)
    private List<ExamResult> examResult;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST})
    @JoinTable(
            name = "e_user_access_group",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "access_group_id", referencedColumnName = "access_group_id")
    )
    private List<AccessGroup> accessGroups;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "graduated_flag", nullable = false, columnDefinition = "boolean default false")
    @JdbcTypeCode(SqlTypes.BOOLEAN)
    private Boolean graduatedFlag = false;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "deleted_flag", nullable = false, columnDefinition = "boolean default false")
    @JdbcTypeCode(SqlTypes.BOOLEAN)
    private Boolean deletedFlag = false;
}
