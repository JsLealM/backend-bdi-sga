package db.sga.backend.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import db.sga.backend.model.Professor;
import db.sga.backend.service.ProfessorService;

/**
 * REST controller that provides endpoints for managing Professor entities.
 * Supports operations for retrieving, creating, updating, and deleting professors.
 */
@RestController
@RequestMapping("/professors/")
public class ProfessorREST {

    @Autowired
    private ProfessorService professorService;

    /**
     * Retrieves a list of all professors.
     *
     * @return HTTP 200 OK with the list of professors in the response body.
     */
    @GetMapping
    public ResponseEntity<List<Professor>> getAllProfessors() {
        return ResponseEntity.ok(professorService.findAll());
    }

    /**
     * Creates a new professor.
     *
     * @param professor The professor object to be saved.
     * @return HTTP 201 Created with the saved professor in the response body and URI in the header,
     *         or HTTP 400 Bad Request if the operation fails.
     */
    @PostMapping
    public ResponseEntity<Professor> saveProfessor(@RequestBody Professor professor) {
        try {
            Professor saved = professorService.save(professor);
            return ResponseEntity.created(new URI("/professors/" + saved.getProfessorId())).body(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Updates an existing professor based on the given ID.
     *
     * @param id The ID of the professor to be updated.
     * @param professor The updated professor data.
     * @return HTTP 200 OK with the updated professor,
     *         HTTP 404 Not Found if the professor does not exist,
     *         or HTTP 500 Internal Server Error if the operation fails.
     */
    @PutMapping("update/{id}")
    private ResponseEntity<Professor> updateProfessor(@PathVariable("id") Long id, @RequestBody Professor professor) {
        try {
            if (!professorService.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            professor.setProfessorId(id);
            Professor updatedProfessor = professorService.save(professor);
            return ResponseEntity.ok(updatedProfessor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Deletes a professor and any associated course assignments based on the given ID.
     *
     * @param id The ID of the professor to be deleted.
     * @return HTTP 200 OK with confirmation message,
     *         HTTP 404 Not Found if the professor does not exist,
     *         or HTTP 500 Internal Server Error if deletion fails.
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteProfessor(@PathVariable("id") Long id) {
        try {
            if (!professorService.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor not found");
            }

            boolean deleted = professorService.deleteProfessorAndAssignments(id);
            if (deleted) {
                return ResponseEntity.ok("Professor and assignments successfully deleted");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                     .body("Failed to delete professor.");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error: " + e.getMessage());
        }
    }
}
