package com.abelardo.MsAnalisis.service;

import com.abelardo.MsAnalisis.persistence.identity.Quality;
import com.abelardo.MsAnalisis.persistence.repository.QualityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class QualityService {

    private final QualityRepository repository;

    public QualityService(QualityRepository repository){
        this.repository=repository;
    }

    public Quality createQuality(Quality quality){
        return this.repository.save(quality);
    }

    public List<Quality> findall(){
        return this.repository.findAll();
    }

    public Optional<Quality> findById(Long id){
        return this.repository.findById(id);

    }

    public Quality updateQuality (Quality quality){
        return this.repository.save(quality);
    }

    public Optional<Quality> deleteQuality(Long id){
        this.repository.deleteById(id);
        return this.repository.findById(id);
    }

}
