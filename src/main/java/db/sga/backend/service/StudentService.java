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
 * Service layer for Student entity.
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentAuditRepository auditRepository;

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public List<Student> findAll(Sort sort) {
        return studentRepository.findAll(sort);
    }

    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    public Optional<Student> findById(Integer id) {
        return studentRepository.findById(id);
    }

    public boolean existsById(Integer id) {
        return studentRepository.existsById(id);
    }

    /**
     * Guarda un estudiante. Si existe, registra auditoría del cambio.
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
                .operationUser("system")
                .build();
            auditRepository.save(audit);
        }
        return studentRepository.save(student);
    }

    /**
     * Elimina estudiante y registros de auditoría asociados.
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
