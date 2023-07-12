package com.abelardo.MsInfoTank.service;

import com.abelardo.MsInfoTank.mapper.InDTOToTank;
import com.abelardo.MsInfoTank.persistence.repository.TankReposotory;
import com.abelardo.MsInfoTank.service.dto.InDTOTank;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.abelardo.MsInfoTank.persistence.indentity.Tank;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class TankService {

    private final TankReposotory tankReposotory;
    private InDTOToTank inDTOToTank;

    public TankService(TankReposotory repository, InDTOToTank inDTOToTank){

        this.tankReposotory = repository;
        this.inDTOToTank= inDTOToTank;
    }

    public Tank createTank(InDTOTank inDTOTank){
        Tank tank = inDTOToTank.map(inDTOTank);
        return this.tankReposotory.save(tank);
    }

    public List<Tank> findAll() {
        return this.tankReposotory.findAll();
    }

    public Page<Tank> findAll(Pageable paginacion) {

        return this.tankReposotory.findAll(paginacion);
    }


    public Optional<Tank> findById(Long id){
        return this.tankReposotory.findById(id);
    }

    public Tank updateTank(Tank tank){ return this.tankReposotory.save(tank);
    }

    public void deleteTank(Long id){
         this.tankReposotory.deleteById(id);

    }

}

