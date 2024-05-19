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
@SQLDelete(sql = "UPDATE public.e_question SET deleted_flag = true WHERE public.e_question.question_id = ?")
@FilterDef(name = "questionDeletedFlagFilter", parameters = @ParamDef(name = "deletedFlag", type = Boolean.class))
@Filter(name = "questionDeletedFlagFilter", condition = "deleted_flag = :deletedFlag")
@Table(name = "e_question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "e_question_seq")
    @SequenceGenerator(name = "e_question_seq")
    @Column(name = "question_id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long questionId;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "question", nullable = false, length = 200)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String question;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, targetEntity = Answer.class, orphanRemoval = true)
    private List<Answer> answers;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "multiple_choice_flag", nullable = false, columnDefinition = "boolean default false")
    @JdbcTypeCode(SqlTypes.BOOLEAN)
    private Boolean multipleChoiceFlag = false;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = QuestionBank.class)
    @JoinColumn(name = "question_bank_id", referencedColumnName = "question_bank_id")
    private QuestionBank questionBank;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "deleted_flag", nullable = false, columnDefinition = "boolean default false")
    @JdbcTypeCode(SqlTypes.BOOLEAN)
    private Boolean deletedFlag = false;
}