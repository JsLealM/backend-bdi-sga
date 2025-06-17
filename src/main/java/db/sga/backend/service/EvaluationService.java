package db.sga.backend.service;

import db.sga.backend.model.Evaluation;
import db.sga.backend.repository.EvaluationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for handling Evaluation logic.
 * Provides methods for CRUD operations and course-specific evaluation retrieval.
 */
@Service
public class EvaluationService {
    @Autowired
    private EvaluationRepository evaluationRepository;

    /**
     * Get all evaluations associated with a specific course.
     * @param courseId ID of the course.
     * @return List of evaluations for the given course.
     */
    public List<Evaluation> findAllByCourse(Long courseId) {
        List<Evaluation> evaluationsAnswer = new ArrayList<>();
        List<Evaluation> evaluations = evaluationRepository.findAll();
        for (Evaluation evaluation : evaluations) {
            if (evaluation.getCourse().getCourseID().equals(courseId)) {
                evaluationsAnswer.add(evaluation);
            }
        }
        return evaluationsAnswer;
    }

    /**
     * Get all evaluations.
     * @return List of all evaluations.
     */
    public List<Evaluation> findAll() {
        return evaluationRepository.findAll();
    }

    /**
     * Get all evaluations with sorting.
     * @param sort Sort specification.
     * @return Sorted list of evaluations.
     */
    public List<Evaluation> findAll(Sort sort) {
        return evaluationRepository.findAll(sort);
    }

    /**
     * Find evaluation by ID.
     * @param id Evaluation ID.
     * @return Optional containing the evaluation if found.
     */
    public Optional<Evaluation> findById(Long id) {
        return evaluationRepository.findById(id);
    }

    /**
     * Check if evaluation exists by ID.
     * @param id Evaluation ID.
     * @return true if exists, false otherwise.
     */
    public boolean existsById(Long id) {
        return evaluationRepository.existsById(id);
    }

    /**
     * Save an evaluation.
     * @param entity Evaluation entity to save.
     * @return Saved evaluation.
     */
    public <S extends Evaluation> S save(S entity) {
        return evaluationRepository.save(entity);
    }

    /**
     * Delete an evaluation by ID, including related grades.
     * @param id Evaluation ID.
     */
    @Transactional
    public void deleteById(Long id) {
        evaluationRepository.deleteGradesByEvaluationId(id);
        evaluationRepository.deleteById(id);
    }

    /**
     * Delete an evaluation entity.
     * @param evaluation Evaluation to delete.
     */
    @Transactional
    public void delete(Evaluation evaluation) {
        deleteById(evaluation.getEvaluationID());
    }
}