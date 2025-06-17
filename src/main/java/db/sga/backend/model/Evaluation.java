package db.sga.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Entity representing an evaluation.
 */
@Entity
@Data
@Table(name = "evaluation", schema = "academic")
public class Evaluation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evaluation_id")
    private Long evaluationID;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public Course getCourse() {
        return this.course;
    }

    public Long getEvaluationID() {
        return evaluationID;
    }

    public void setEvaluationID(Long evaluationID) {
        this.evaluationID = evaluationID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
