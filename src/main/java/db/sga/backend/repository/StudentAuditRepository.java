package db.sga.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import db.sga.backend.model.StudentAudit;

@Repository
public interface StudentAuditRepository extends JpaRepository<StudentAudit, Integer> {
}
