package db.sga.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import db.sga.backend.model.Student;
import jakarta.transaction.Transactional;

/**
 * Repository interface for Student entity.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    /**
     * Deletes the audit records of a specific student.
     * @param studentId ID student.
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM academic.student_audit WHERE student_id = :studentId", nativeQuery = true)
    void deleteAuditsByStudentId(@Param("studentId") Integer studentId);
}
