package db.sga.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import db.sga.backend.model.Student;
import db.sga.backend.repository.StudentRepository;

/**
 * Service layer for Student entity.
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public boolean existsById(Integer id) {
        return studentRepository.existsById(id);
    }

    /**
     * Guarda un estudiante.
     */
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    /**
     * Elimina estudiante y registros relacionados.
     */
    @Transactional
    public boolean deleteStudent(Integer id) {
        if (!studentRepository.existsById(id)) {
            return false;
        }
        // Eliminar registros relacionados primero
        studentRepository.deleteEnrollmentsByStudentId(id);
        studentRepository.deleteGradeEvaluationByStudentId(id);
        // Eliminar el estudiante
        studentRepository.deleteById(id);
        return !studentRepository.existsById(id);
    }
}