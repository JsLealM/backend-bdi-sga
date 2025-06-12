package db.sga.backend.model;
import db.sga.backend.model.Course;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table (name = "evaluation", schema = "academic")
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evaluation_id")
    private int evaluationID;

    @Column(name = "type",nullable = false)
    private String type;

    @Column(name ="date",nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "course_id",nullable = false)
    private Course course;

    public Evaluation(){
    }

    public Evaluation(String type, LocalDate date, Course course) {
        this.type = type;
        this.date = date;
        this.course = course;
    }

    public int getEvaluationID() {
        return evaluationID;
    }

    public void setEvaluationID(int evaluationID) {
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

    public Course getCourse() {
        return course;
    }

    public int getCourseId(){
        return this.course.getCourseID();
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
