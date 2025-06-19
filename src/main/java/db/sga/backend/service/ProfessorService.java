package db.sga.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import db.sga.backend.model.Professor;
import db.sga.backend.repository.ProfessorRepository;

/**
 * Service layer responsible for business logic and data access operations
 * related to Professor entities.
 *
 * Delegates persistence operations to the {@link ProfessorRepository}.
 */
@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    /**
     * Constructor injection for the ProfessorRepository dependency.
     *
     * @param professorRepository the repository for professor entities.
     */
    @Autowired
    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    /**
     * Retrieves all professors from the database.
     *
     * @return a list of all professors.
     */
    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    /**
     * Retrieves all professors sorted according to the provided Sort object.
     *
     * @param sort the sorting criteria.
     * @return a sorted list of professors.
     */
    public List<Professor> findAll(Sort sort) {
        return professorRepository.findAll(sort);
    }

    /**
     * Retrieves a paginated list of professors.
     *
     * @param pageable the pagination information.
     * @return a page of professors.
     */
    public Page<Professor> findAll(Pageable pageable) {
        return professorRepository.findAll(pageable);
    }

    /**
     * Saves or updates a professor entity.
     *
     * @param entity the professor to be saved.
     * @return the saved or updated professor.
     */
    public <S extends Professor> S save(S entity) {
        return professorRepository.save(entity);
    }

    /**
     * Finds a professor by its unique ID.
     *
     * @param id the professor ID.
     * @return an Optional containing the professor if found, otherwise empty.
     */
    public Optional<Professor> findById(Long id) {
        return professorRepository.findById(id);
    }

    /**
     * Checks if a professor exists by ID.
     *
     * @param id the professor ID.
     * @return true if the professor exists, false otherwise.
     */
    public boolean existsById(Long id) {
        return professorRepository.existsById(id);
    }

    /**
     * Deletes a given professor entity.
     *
     * @param entity the professor to delete.
     */
    public void delete(Professor entity) {
        professorRepository.delete(entity);
    }

    /**
     * Deletes a professor and all associated course assignments within a transactional context.
     * Ensures related entities are removed in the correct order.
     *
     * @param id the ID of the professor to delete.
     * @return true if deletion was successful, false otherwise.
     */
    @Transactional
    public boolean deleteProfessorAndAssignments(Long id) {
        if (professorRepository.existsById(id)) {
            professorRepository.deleteByProfessorId(id); // delete related course assignments
            professorRepository.deleteById(id);          // then delete the professor
            return !professorRepository.existsById(id);  // verify deletion
        }
        return false;
    }
}
