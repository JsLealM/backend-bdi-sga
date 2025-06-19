package db.sga.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Represents a Professor entity mapped to the "professor" table
 * in the "academic" schema of the database.
 * 
 * This entity is used to store and retrieve professor information 
 * such as name, email, and academic grade.
 *
 * Lombok's @Data annotation is used to automatically generate 
 * getters, setters, toString(), equals(), and hashCode() methods.
 */
@Entity
@Data
@Table(name = "professor", schema = "academic")
public class Professor {

    /**
     * Unique identifier for the professor.
     * This field must be set manually, as the ID is not auto-generated.
     */
    @Id
    @Column(name = "professor_id", nullable = false)
    private Long professorId;

    /**
     * The professor's first name.
     * Cannot be null.
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * The professor's last name.
     * Cannot be null.
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * The professor's email address.
     * Must be unique and not null.
     */
    @Column(name = "email", nullable = false)
    private String email;

    /**
     * The academic grade or title of the professor.
     * Example values: "Mathematic", "Education", etc.
     */
    @Column(name = "grade", nullable = false)
    private String grade;
}
