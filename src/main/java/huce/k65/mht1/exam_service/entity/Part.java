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
@SQLDelete(sql = "UPDATE public.e_part SET deleted_flag = true WHERE public.e_part.part_id = ?")
@FilterDef(name = "partDeletedFlagFilter", parameters = @ParamDef(name = "deletedFlag", type = Boolean.class))
@Filter(name = "partDeletedFlagFilter", condition = "deleted_flag = :deletedFlag")
@Table(name = "e_part")
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "e_part_seq")
    @SequenceGenerator(name = "e_part_seq")
    @Column(name = "part_id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long partId;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "part_name", nullable = false, length = 50)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String partName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Exam.class)
    @JoinColumn(name = "exam_id", referencedColumnName = "exam_id")
    private Exam exam;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = QuestionBank.class)
    @JoinColumn(name = "question_bank_id", referencedColumnName = "question_bank_id")
    private QuestionBank questionBank;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "deleted_flag", nullable = false, columnDefinition = "boolean default false")
    @JdbcTypeCode(SqlTypes.BOOLEAN)
    private Boolean deletedFlag = false;
}