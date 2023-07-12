package com.abelardo.MsAnalisis.service;

import com.abelardo.MsAnalisis.persistence.identity.Quality;
import com.abelardo.MsAnalisis.persistence.repository.QualityRepository;
import com.abelardo.MsAnalisis.service.dto.InDTOQuality;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.abelardo.MsAnalisis.mapper.InDTOToQuality;

import java.util.List;
import java.util.Optional;


@Service
public class QualityService {

    private final QualityRepository repository;

    private InDTOToQuality inDTOToQuality;

    public QualityService(QualityRepository repository, InDTOToQuality inDTOToQuality){

        this.repository=repository;
        this.inDTOToQuality=inDTOToQuality;
    }

    public Quality createQuality(InDTOQuality inDTOQuality){
        Quality quality = inDTOToQuality.map(inDTOQuality);
        return this.repository.save(quality);
    }

    public Page<Quality> findall(Pageable paginacion){

        return this.repository.findAll(paginacion);
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
