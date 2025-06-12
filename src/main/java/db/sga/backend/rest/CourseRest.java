package db.sga.backend.rest;

import db.sga.backend.model.Course;
import db.sga.backend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/course/")
public class CourseRest {

    @Autowired
    private CourseService courseService;

    @GetMapping
    private ResponseEntity<List<Course>> getAllCourses (){
        return ResponseEntity.ok(courseService.findAll());
    }

    @PostMapping
    private ResponseEntity<Course> saveCourse(@RequestBody Course course){

        try {
            Course savedCourse = courseService.save(course);
            return ResponseEntity.created(new URI("/course" + course.getCourseID())).body(savedCourse);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
}
