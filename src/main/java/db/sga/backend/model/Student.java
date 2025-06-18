package db.sga.backend.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entity representing a student.
 */
@Entity
@Data
@Table(name = "student", schema = "academic")
public class Student implements Serializable {

    @Id
    @Column(name = "student_id", nullable = false)
    private Integer studentId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "maternal_surname")
    private String maternalSurname;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
}
