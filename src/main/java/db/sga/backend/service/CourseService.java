package db.sga.backend.service;

import db.sga.backend.model.Course;
import db.sga.backend.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService{

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public <S extends Course> S save(S entity) {
        return courseRepository.save(entity);
    }

    public List<Course> findAll(Sort sort) {
        return courseRepository.findAll(sort);
    }
}
