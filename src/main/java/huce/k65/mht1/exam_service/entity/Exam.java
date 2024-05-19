package huce.k65.mht1.exam_service.entity;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.type.SqlTypes;

import java.time.Duration;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE public.e_exam SET deleted_flag = true WHERE public.e_exam.exam_id = ?")
@FilterDef(name = "examDeletedFlagFilter", parameters = @ParamDef(name = "deletedFlag", type = Boolean.class))
@Filter(name = "examDeletedFlagFilter", condition = "deleted_flag = :deletedFlag")
@Table(name = "e_exam")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "e_exam_seq")
    @SequenceGenerator(name = "e_exam_seq")
    @Column(name = "exam_id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long examId;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "exam_name", nullable = false, length = 50)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String examName;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "exam_duration", nullable = false)
    @JdbcTypeCode(SqlTypes.TIME)
    private Duration examDuration;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "note")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String note;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, targetEntity = AccessGroup.class)
    private List<AccessGroup> accessGroups;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, targetEntity = Part.class)
    private List<Part> parts;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", referencedColumnName = "user_id")
    private User creator;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, targetEntity = ExamResult.class)
    private List<ExamResult> examResult;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "repeatable_limit", nullable = false, columnDefinition = "integer default 0")
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Integer repeatableLimit;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "repeatable_count", nullable = false, columnDefinition = "integer default 0")
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Integer repeatableCount;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "reviewable_after_finish_flag", nullable = false, columnDefinition = "boolean default false")
    @JdbcTypeCode(SqlTypes.BOOLEAN)
    private Boolean reviewableAfterFinishFlag = false;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "deleted_flag", nullable = false, columnDefinition = "boolean default false")
    @JdbcTypeCode(SqlTypes.BOOLEAN)
    private Boolean deleted_flag = false;
}