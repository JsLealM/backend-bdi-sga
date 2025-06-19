package db.sga.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import db.sga.backend.model.Professor;
import jakarta.transaction.Transactional;

/**
 * Repository interface for performing CRUD operations on Professor entities.
 * Extends JpaRepository to provide default methods for entity management.
 */
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    /**
     * Deletes all course assignments associated with a given professor.
     * This operation executes a native SQL query on the 'course_assignment' table
     * within the 'academic' schema.
     *
     * This method is annotated with @Modifying and @Transactional to indicate
     * that it performs a write operation and should be executed within a transaction.
     *
     * @param professorId The ID of the professor whose course assignments are to be deleted.
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM academic.course_assignment WHERE professor_id = :professorId", nativeQuery = true)
    void deleteByProfessorId(@Param("professorId") Long professorId);
}
