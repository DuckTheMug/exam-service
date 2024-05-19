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

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE public.e_question_bank SET deleted_flag = true WHERE public.e_question_bank.question_bank_id = ?")
@FilterDef(name = "questionBankDeletedFlagFilter", parameters = @ParamDef(name = "deletedFlag", type = Boolean.class))
@Filter(name = "questionBankDeletedFlagFilter", condition = "deleted_flag = :deletedFlag")
@Table(name = "e_question_bank")
public class QuestionBank {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "e_question_bank_seq")
    @SequenceGenerator(name = "e_question_bank_seq")
    @Column(name = "question_bank_id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long questionBankId;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "question_bank_name", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String questionBankName;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "note")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String note;

    @OneToMany(mappedBy = "questionBank" , fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            targetEntity = Question.class)
    private List<Question> questions;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Part.class)
    @JoinColumn(name = "part_id", referencedColumnName = "part_id")
    private Part part;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "deleted_flag", nullable = false, columnDefinition = "boolean default false")
    @JdbcTypeCode(SqlTypes.BOOLEAN)
    private Boolean deletedFlag = false;
}