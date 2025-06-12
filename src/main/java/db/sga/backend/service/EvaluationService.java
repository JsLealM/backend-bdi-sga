package db.sga.backend.service;

import db.sga.backend.model.Evaluation;
import db.sga.backend.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class EvaluationService implements EvaluationRepository{

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Override
    public void flush() {

    }

    @Override
    public <S extends Evaluation> S saveAndFlush(S entity) {
        return null;
    }

    public List<Evaluation> findAllByCourse (Long  courseId){
        List<Evaluation> evaluationsAnswer = new ArrayList<>();
        List<Evaluation> evaluations = evaluationRepository.findAll();
        for (int i = 0; i < evaluations.size(); i++){
            if (evaluations.get(i).getCourse().getCourseID() == courseId){
                evaluationsAnswer.add(evaluations.get(i));
            }
        }
        return evaluationsAnswer;
    }

    //Find Evaluation
    @Override
    public List<Evaluation> findAll() {
        return evaluationRepository.findAll();
    }

    @Override
    public List<Evaluation> findAll(Sort sort) {
        return evaluationRepository.findAll(sort);
    }

    //Save Evaluation
    @Override
    public <S extends Evaluation> S save(S entity) {
        return evaluationRepository.save(entity);
    }

    @Override
    public void deleteById(Long aLong) {
        evaluationRepository.deleteById(aLong);
    }

    @Override
    public void delete(Evaluation entity) {
        evaluationRepository.delete(entity);
    }



    @Override
    public <S extends Evaluation> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<Evaluation> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Evaluation getOne(Long aLong) {
        return null;
    }

    @Override
    public Evaluation getById(Long aLong) {
        return null;
    }

    @Override
    public Evaluation getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Evaluation> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Evaluation> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Evaluation> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Evaluation> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Evaluation> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Evaluation> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Evaluation, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Evaluation> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<Evaluation> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Evaluation> findAllById(Iterable<Long> longs) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }


    @Override
    public void deleteAllById(Iterable<? extends Long> longs) { }



    @Override
    public void deleteAll(Iterable<? extends Evaluation> entities) { }

    @Override
    public void deleteAll() { }

    @Override
    public Page<Evaluation> findAll(Pageable pageable) {
        return null;
    }
}
