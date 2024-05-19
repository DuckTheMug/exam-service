package huce.k65.mht1.exam_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "e_access_group")
public class AccessGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "e_access_group_seq")
    @SequenceGenerator(name = "e_access_group_seq")
    @Column(name = "access_group_id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long accessGroupId;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "access_group_name", nullable = false, length = 50)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String accessGroupName;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "access_start_date_and_time", nullable = false)
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    private Timestamp accessStartDateAndTime;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "access_end_date_and_time", nullable = false)
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    private Timestamp accessEndDateAndTime;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST},
            mappedBy = "accessGroups")
    private List<User> users;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Exam.class)
    @JoinColumn(name = "exam_id", referencedColumnName = "exam_id")
    private Exam exam;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "deleted_flag", nullable = false, columnDefinition = "boolean default false")
    @JdbcTypeCode(SqlTypes.BOOLEAN)
    private Boolean deletedFlag = false;
}