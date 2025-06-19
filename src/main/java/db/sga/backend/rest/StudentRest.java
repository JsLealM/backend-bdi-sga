package db.sga.backend.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import db.sga.backend.model.Student;
import db.sga.backend.service.StudentService;

/**
 * REST Controller for managing Student entities.
 * <p>
 * Provides endpoints to:
 * - Retrieve all students
 * - Create a new student
 * - Update an existing student
 * - Delete a student along with their audit records
 */
@RestController
@RequestMapping("/students/")
public class StudentRest {

    @Autowired
    private StudentService studentService;

    /**
     * Retrieves all registered students.
     *
     * @return List of students in the system
     */
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAll());
    }

    /**
     * Saves a new student to the database.
     *
     * @param student the student object to save
     * @return Response with created student and URI location header, or 400 if invalid
     */
    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        try {
            Student saved = studentService.save(student);
            return ResponseEntity
                    .created(new URI("/students/" + saved.getStudentId()))
                    .body(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }

    /**
     * Updates an existing student.
     *
     * @param id      the ID of the student to update
     * @param student updated student data
     * @return Response with updated student, or appropriate error status
     */
    @PutMapping("update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Integer id, @RequestBody Student student) {
        try {
            if (!studentService.existsById(id)) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .build();
            }

            student.setStudentId(id);
            Student updated = studentService.save(student);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    /**
     * Deletes a student and their associated audit records.
     *
     * @param id the ID of the student to delete
     * @return Success message or error response
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Integer id) {
        try {
            if (!studentService.existsById(id)) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Student not found.");
            }

            boolean deleted = studentService.deleteStudentAndAudits(id);
            if (deleted) {
                return ResponseEntity.ok("Student and audit logs successfully deleted.");
            } else {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to delete student.");
            }

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error: " + e.getMessage());
        }
    }
}
