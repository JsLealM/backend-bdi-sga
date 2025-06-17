package db.sga.backend.rest;

import db.sga.backend.model.Evaluation;
import db.sga.backend.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * REST Controller for managing evaluations.
 * Provides endpoints for CRUD operations and course-specific retrieval.
 */
@RestController
@RequestMapping("/evaluation/")
public class EvaluationRest {

    @Autowired
    private EvaluationService evaluationService;

    /**
     * Get all evaluations.
     * @return List of all evaluations.
     */
    @GetMapping
    private ResponseEntity<List<Evaluation>> getAllEvaluation() {
        return ResponseEntity.ok(evaluationService.findAll());
    }

    /**
     * Get evaluations by course ID.
     * @param courseId Course ID.
     * @return List of evaluations for the given course.
     */
    @GetMapping("/course/{courseId}")
    private ResponseEntity<List<Evaluation>> getAllEvaluation(@PathVariable("courseId") Long courseId) {
        return ResponseEntity.ok(evaluationService.findAllByCourse(courseId));
    }

    /**
     * Save a new evaluation.
     * @param evaluation Evaluation to save.
     * @return Saved evaluation with location header.
     */
    @PostMapping
    private ResponseEntity<Evaluation> saveEvaluation(@RequestBody Evaluation evaluation) {
        try {
            Evaluation savedEvaluation = evaluationService.save(evaluation);
            return ResponseEntity.created(new URI("/evaluation/" + evaluation.getEvaluationID())).body(savedEvaluation);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Update an existing evaluation.
     * @param id Evaluation ID.
     * @param evaluation Updated evaluation data.
     * @return Updated evaluation.
     */
    @PutMapping("update/{id}")
    private ResponseEntity<Evaluation> updateEvaluation(@PathVariable("id") Long id, @RequestBody Evaluation evaluation) {
        try {
            evaluation.setEvaluationID(id);
            Evaluation updatedEvaluation = evaluationService.save(evaluation);
            return ResponseEntity.ok(updatedEvaluation);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Delete an evaluation by ID.
     * @param id Evaluation ID.
     * @return Response message.
     */
    @DeleteMapping("delete/{id}")
    private ResponseEntity<String> deleteEvaluation(@PathVariable("id") Long id) {
        try {
            if (!evaluationService.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evaluación no encontrada");
            }
            evaluationService.deleteById(id);
            return ResponseEntity.ok("Evaluación eliminada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }
}
