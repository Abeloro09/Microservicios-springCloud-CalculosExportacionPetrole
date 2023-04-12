package com.abelardo.MsInfoTank.controller;

import com.abelardo.MsInfoTank.persistence.indentity.Tank;
import com.abelardo.MsInfoTank.service.TankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class TankController {

    private final TankService tankService;

    public TankController(TankService tankService) {
        this.tankService = tankService;
    }
    @PostMapping
    public Tank createTank(@RequestBody Tank tank){
        return this.tankService.createTank(tank);
    }

    @GetMapping
    public List<Tank> findAll(){
        return this.tankService.findAll();
    }

    /*@GetMapping
    public ResponseEntity<List<Tank>> findAll(){
        List<Tank> tanks =tankService.findAll();
        if(tanks.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tanks);
    }
*/
    @GetMapping("/byId/{id}")
    public Optional<Tank> findById(@PathVariable("id") Long id){
        Optional<Tank> result = this.tankService.findById(id);
        return result;
    }

    @PutMapping("/update/{id}")
    public Tank update(@PathVariable("id") Long id, @RequestBody Tank tank){
        Tank updatedTank = tankService.findById(id).get();

        updatedTank.setNombreTk(tank.getNombreTk());
        updatedTank.setCapacidadNominal(tank.getCapacidadNominal());
        updatedTank.setInicioZonaCritica(tank.getInicioZonaCritica());
        updatedTank.setFinalZonaCritica(tank.getFinalZonaCritica());
        updatedTank.setTempLamina(tank.getTempLamina());
        updatedTank.setFra1(tank.getFra1());
        updatedTank.setFra2(tank.getFra2());

        tankService.updateTank(updatedTank);

        return updatedTank;

    }

    @DeleteMapping("/delete/{id}")
    public List<Tank> deleteTank(@PathVariable("id") Long id){
        tankService.deleteTank(id);
        return this.tankService.findAll();

    }





}


