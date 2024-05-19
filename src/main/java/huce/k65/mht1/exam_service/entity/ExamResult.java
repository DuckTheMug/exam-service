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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE public.e_exam_result SET deleted_flag = true WHERE public.e_exam_result.exam_result_id = ?")
@FilterDef(name = "examResultDeletedFlagFilter", parameters = @ParamDef(name = "deletedFlag", type = Boolean.class))
@Filter(name = "examResultDeletedFlagFilter", condition = "deleted_flag = :deletedFlag")
@Table(name = "e_exam_result")
public class ExamResult {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "e_exam_result_seq")
    @SequenceGenerator(name = "e_exam_result_seq")
    @Column(name = "exam_result_id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long examResultId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Exam.class)
    @JoinColumn(name = "exam_id", referencedColumnName = "exam_id")
    private Exam exam;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "total_score", nullable = false)
    @JdbcTypeCode(SqlTypes.FLOAT)
    private Float totalScore;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "deleted_flag", nullable = false, columnDefinition = "boolean default false")
    @JdbcTypeCode(SqlTypes.BOOLEAN)
    private Boolean deletedFlag = false;
}