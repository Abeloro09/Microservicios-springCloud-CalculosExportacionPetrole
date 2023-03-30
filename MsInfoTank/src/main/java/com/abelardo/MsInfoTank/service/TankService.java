package com.abelardo.MsInfoTank.service;

import com.abelardo.MsInfoTank.persistence.repository.TankReposotory;
import org.springframework.stereotype.Service;
import com.abelardo.MsInfoTank.persistence.indentity.Tank;

import java.util.List;
import java.util.Optional;

@Service
public class TankService {

    private final TankReposotory repository;

    public TankService(TankReposotory repository){
        this.repository = repository;
    }

    public Tank createTank(Tank tank){
        return this.repository.save(tank);
    }

    public List<Tank> findAll() {
        return this.repository.findAll();
    }

    public Optional<Tank> findById(Long id){
        return this.repository.findById(id);
    }

    public Tank updateTank(Tank tank){ return this.repository.save(tank);
    }

    public List<Tank> deleteTank(Long id){
         this.repository.deleteById(id);
        return this.repository.findAll();
    }

}

