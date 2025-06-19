package db.sga.backend.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import db.sga.backend.model.Student;
import db.sga.backend.model.StudentAudit;
import db.sga.backend.repository.StudentAuditRepository;
import db.sga.backend.repository.StudentRepository;

/**
 * Service layer for managing Student entities and audit records.
 * <p>
 * Provides high-level business logic for retrieving, saving, updating,
 * and deleting student records, along with logging changes in audit history.
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentAuditRepository auditRepository;

    /**
     * Retrieves all students without sorting or pagination.
     *
     * @return a list of all students in the database
     */
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    /**
     * Retrieves all students sorted by the given criteria.
     *
     * @param sort the sort specification
     * @return sorted list of students
     */
    public List<Student> findAll(Sort sort) {
        return studentRepository.findAll(sort);
    }

    /**
     * Retrieves students with pagination support.
     *
     * @param pageable pagination and sorting configuration
     * @return a page of students
     */
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    /**
     * Retrieves a student by their unique ID.
     *
     * @param id the student ID
     * @return an Optional containing the student if found, or empty otherwise
     */
    public Optional<Student> findById(Integer id) {
        return studentRepository.findById(id);
    }

    /**
     * Checks whether a student exists with the given ID.
     *
     * @param id the student ID
     * @return true if the student exists, false otherwise
     */
    public boolean existsById(Integer id) {
        return studentRepository.existsById(id);
    }

    /**
     * Saves a new student or updates an existing one.
     * <p>
     * If the student already exists, an audit entry is created to log the update.
     *
     * @param student the student entity to save
     * @return the saved or updated student
     */
    public Student save(Student student) {
        Student existing = studentRepository.findById(student.getStudentId()).orElse(null);
        if (existing != null) {
            StudentAudit audit = StudentAudit.builder()
                .actionType("UPDATE")
                .studentId(student.getStudentId())
                .oldFirstName(existing.getFirstName())
                .oldLastName(existing.getLastName())
                .newFirstName(student.getFirstName())
                .newLastName(student.getLastName())
                .actionTimestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .operationUser("system") // Replace with actual user in a real environment
                .build();
            auditRepository.save(audit);
        }
        return studentRepository.save(student);
    }

    /**
     * Deletes a student and all associated audit records.
     * <p>
     * This is a transactional operation that ensures both the student and their audit
     * entries are removed in a single atomic operation.
     *
     * @param id the ID of the student to delete
     * @return true if the student was successfully deleted, false otherwise
     */
    @Transactional
    public boolean deleteStudentAndAudits(Integer id) {
        if (!studentRepository.existsById(id)) {
            return false;
        }

        auditRepository.deleteAll(
            auditRepository.findAll().stream()
                .filter(a -> a.getStudentId().equals(id))
                .toList()
        );

        studentRepository.deleteById(id);
        return !studentRepository.existsById(id);
    }
}
