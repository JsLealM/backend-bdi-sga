package db.sga.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "student_audit", schema = "academic")
public class StudentAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Integer logId;

    @Column(name = "action_type")
    private String actionType; // UPDATE or DELETE

    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "old_first_name")
    private String oldFirstName;

    @Column(name = "old_last_name")
    private String oldLastName;

    @Column(name = "new_first_name")
    private String newFirstName;

    @Column(name = "new_last_name")
    private String newLastName;

    @Column(name = "action_timestamp")
    private String actionTimestamp;

    @Column(name = "operation_user")
    private String operationUser;
}