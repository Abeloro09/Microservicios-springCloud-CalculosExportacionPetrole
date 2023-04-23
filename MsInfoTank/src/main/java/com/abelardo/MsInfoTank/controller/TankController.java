package com.abelardo.MsInfoTank.controller;

import com.abelardo.MsInfoTank.persistence.indentity.Tank;
import com.abelardo.MsInfoTank.service.TankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RefreshScope
@RestController
public class TankController {

    @Autowired
    private Environment env;

    @Value("${configuracion.texto}")
    private String texto;
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

    @GetMapping("/obtener-config")
    public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String puerto){

        Map<String, String> json = new HashMap<>();
        json.put("texto", texto);
        json.put("puerto", puerto);

        if(env.getActiveProfiles().length>0 && env.getActiveProfiles()[0].equals("desarrollo"))
        {
            json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
            json.put("autor.email", env.getProperty("configuracion.autor.email"));

        }
        return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
    }


}


