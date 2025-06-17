package db.sga.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Entity
@Data
@Table(name = "course", schema = "academic")
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseID;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "credits", nullable = false)
    private int credits;

    @Column(name = "type",nullable = false)
    private String type;

    public Long getCourseID() {
        return courseID;
    }
    public void setCourseID(Long courseID) {
        this.courseID = courseID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCredits() {
        return credits;
    }
    public void setCredits(int credits) {
        this.credits = credits;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
