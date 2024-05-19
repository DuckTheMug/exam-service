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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE public.e_belong_class SET deleted_flag = true WHERE public.e_belong_class.belong_class_id = ?")
@FilterDef(name = "belongClassDeletedFlagFilter", parameters = @ParamDef(name = "deletedFlag", type = Boolean.class))
@Filter(name = "belongClassDeletedFlagFilter", condition = "deleted_flag = :deletedFlag")
@Table(name = "e_belong_class")
public class BelongClass {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "e_belong_class_seq")
    @SequenceGenerator(name = "e_class_seq")
    @Column(name = "belong_class_id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long classId;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "belong_class_name", nullable = false, length = 50)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String className;

    @OneToMany(mappedBy = "belongClass", cascade = CascadeType.ALL, targetEntity = User.class)
    private List<User> users;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "deleted_flag", nullable = false, columnDefinition = "boolean default false")
    @JdbcTypeCode(SqlTypes.BOOLEAN)
    private Boolean deletedFlag = false;
}
