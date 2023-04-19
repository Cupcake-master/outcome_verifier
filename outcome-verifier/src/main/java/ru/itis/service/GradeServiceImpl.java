package ru.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.model.Grade;
import ru.itis.repository.GradeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GradeServiceImpl{

    private final GradeRepository gradeRepository;

    @Autowired
    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public Optional<Grade> findById(Long id){
        return gradeRepository.findById(id);
    }

    public List<Grade> findAll(){
        return gradeRepository.findAll();
    }
}
