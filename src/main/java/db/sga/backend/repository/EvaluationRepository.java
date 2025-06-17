package db.sga.backend.repository;

import db.sga.backend.model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Evaluation entity.
 */
@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    /**
     * Custom query to delete related grades before removing evaluation.
     * @param evaluationId ID of the evaluation to delete grades for.
     */
    @Modifying
    @Query(value = "DELETE FROM academic.grade_evaluation WHERE evaluation_id = :evaluationId", nativeQuery = true)
    void deleteGradesByEvaluationId(@Param("evaluationId") Long evaluationId);

}
