package com.abelardo.MsInfoTank.controller;

import com.abelardo.MsInfoTank.mapper.OutDTOToTank;
import com.abelardo.MsInfoTank.persistence.indentity.Tank;
import com.abelardo.MsInfoTank.service.TankService;
import com.abelardo.MsInfoTank.service.dto.InDTOTank;
import com.abelardo.MsInfoTank.service.dto.OutDTOTank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RefreshScope
@RestController
@RequestMapping("/tank")
public class TankController {

    @Autowired
    private Environment env;

    @Value("${configuracion.texto}")
    private String texto;
    private final TankService tankService;
    private final OutDTOToTank outDTOToTank;

    public TankController(TankService tankService, OutDTOToTank outDTOToTank) {

        this.tankService = tankService;
        this.outDTOToTank = outDTOToTank;
    }
    @PostMapping
    public ResponseEntity<Tank> createTank(@RequestBody @Valid InDTOTank inDTOTank, UriComponentsBuilder uriComponentsBuilder){
        Tank tank = this.tankService.createTank(inDTOTank);
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(tank.getId()).toUri();
        return ResponseEntity.created(url).body(tank);

    }

    @GetMapping
    public ResponseEntity<Page<OutDTOTank>> findAll(@PageableDefault(size = 2) Pageable paginacion){

        return ResponseEntity.ok(this.tankService.findAll(paginacion).map(outDTOToTank::map));

    }


    @GetMapping("/byId/{id}")
    public ResponseEntity<Optional<Tank>> findById(@PathVariable("id") Long id){

        Optional <Tank> tankOptional = this.tankService.findById(id);
        if (tankOptional.isPresent()){
            return ResponseEntity.ok(tankOptional);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Tank> update(@PathVariable("id") Long id, @RequestBody @Valid InDTOTank inDTOTank){
        Tank updatedTank = tankService.findById(id).get();

        updatedTank.setNombreTk(inDTOTank.getNombreTk());
        updatedTank.setCapacidadNominal(inDTOTank.getCapacidadNominal());
        updatedTank.setInicioZonaCritica(inDTOTank.getInicioZonaCritica());
        updatedTank.setFinalZonaCritica(inDTOTank.getFinalZonaCritica());
        updatedTank.setTempLamina(inDTOTank.getTempLamina());
        updatedTank.setFra1(inDTOTank.getFra1());
        updatedTank.setFra2(inDTOTank.getFra2());

        tankService.updateTank(updatedTank);

        return ResponseEntity.ok(updatedTank);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTank(@PathVariable("id") Long id){
        tankService.deleteTank(id);
        return ResponseEntity.ok().build();

    }
// implementando metodos para obtener configuraciones
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


