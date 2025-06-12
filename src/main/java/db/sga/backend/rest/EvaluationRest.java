package db.sga.backend.rest;

import db.sga.backend.model.Course;
import db.sga.backend.model.Evaluation;
import db.sga.backend.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/evaluation/")
public class EvaluationRest {

    @Autowired
    private EvaluationService evaluationService;

    @GetMapping
    private ResponseEntity<List<Evaluation>> getAllEvaluation (){
        return ResponseEntity.ok(evaluationService.findAll());
    }

    @GetMapping("/course/{courseId}")
    private ResponseEntity<List<Evaluation>> getAllEvaluation (@PathVariable("courseId") Long courseId){
        return ResponseEntity.ok(evaluationService.findAllByCourse(courseId));
    }

    @PostMapping
    private ResponseEntity<Evaluation> saveEvaluation(@RequestBody Evaluation evaluation){

        try {
            Evaluation savedEvaluation = evaluationService.save(evaluation);
            return ResponseEntity.created(new URI("/evaluation/" + evaluation.getEvaluationID())).body(savedEvaluation);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

}
