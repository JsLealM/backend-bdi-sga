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
 * REST Controller for Student entity.
 */
@RestController
@RequestMapping("/students/")
public class StudentRest {

    @Autowired
    private StudentService studentService;

    /**
     * Get all students.
     * @return List of students.
     */
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAll());
    }

    /**
     * Save a new student.
     * @param student Student object.
     * @return Saved student with location header.
     */
    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        try {
            Student saved = studentService.save(student);
            return ResponseEntity.created(new URI("/students/" + saved.getStudentId())).body(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Update an existing student.
     */
    @PutMapping("update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Integer id, @RequestBody Student student) {
        try {
            if (!studentService.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            student.setStudentId(id);
            Student updated = studentService.save(student);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Delete a student and their audit records.
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Integer id) {
        try {
            if (!studentService.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estudiante no encontrado");
            }

            boolean deleted = studentService.deleteStudentAndAudits(id);
            if (deleted) {
                return ResponseEntity.ok("Estudiante y auditorías eliminados correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                     .body("No se pudo eliminar el estudiante.");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error interno: " + e.getMessage());
        }
    }
}
