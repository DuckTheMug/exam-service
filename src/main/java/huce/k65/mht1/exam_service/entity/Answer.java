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
@SQLDelete(sql = "UPDATE public.e_answer SET deleted_flag = true WHERE public.e_answer.answer_id = ?")
@FilterDef(name = "answerDeletedFlagFilter", parameters = @ParamDef(name = "deletedFlag", type = Boolean.class))
@Filter(name = "answerDeletedFlagFilter", condition = "deleted_flag = :deletedFlag")
@Table(name = "e_answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "e_answer_seq")
    @SequenceGenerator(name = "e_answer_seq")
    @Column(name = "answer_id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long answerId;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "answer", nullable = false, length = 500)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Question.class)
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    private Question question;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "correct_flag", nullable = false, columnDefinition = "boolean default false")
    @JdbcTypeCode(SqlTypes.BOOLEAN)
    private Boolean correctFlag = false;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "score_strength", nullable = false)
    @JdbcTypeCode(SqlTypes.FLOAT)
    private Float scoreStrength;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "anchor_flag", nullable = false, columnDefinition = "boolean default false")
    @JdbcTypeCode(SqlTypes.BOOLEAN)
    private Boolean anchorFlag = false;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "deleted_flag", nullable = false, columnDefinition = "boolean default false")
    @JdbcTypeCode(SqlTypes.BOOLEAN)
    private Boolean deletedFlag = false;
}