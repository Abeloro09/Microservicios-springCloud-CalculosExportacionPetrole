package com.abelardo.MsLiquidacion.controller;

import com.abelardo.MsLiquidacion.persistence.entity.Movimiento;
import com.abelardo.MsLiquidacion.persistence.repository.MovimientoRepository;
import com.abelardo.MsLiquidacion.service.MovimientoService;
import com.abelardo.MsLiquidacion.service.dto.DatosParaEditatLiq;
import com.abelardo.MsLiquidacion.service.dto.LiquidacionInDTO;
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
public class movimientoController {

    @Autowired
    private Environment env;

    @Value("${configuracion.texto}") private String texto;
    private final MovimientoService movimientoService;
    private final MovimientoRepository movimientoRepository;

    public movimientoController(MovimientoService movimientoService,
                                MovimientoRepository movimientoRepository) {
        this.movimientoService = movimientoService;
        this.movimientoRepository = movimientoRepository;
    }


    @PostMapping("/crearmovimiento/{cargueId}")
    public Movimiento createMovimiento(@PathVariable("cargueId") Long cargueId){
        return this.movimientoService.createMovimiento(cargueId);
    }

    @GetMapping
    public List<Movimiento> findAll(){
        return this.movimientoService.findAll();
    }

    @GetMapping("/movimientoById/{id}")
    public Optional<Movimiento> findById(@PathVariable("id") Long id){
        Optional<Movimiento> result = this.movimientoService.findById(id);
        return result;
    }


    @PutMapping("/calcularLiquidacion/{id}/")
    public Optional<Movimiento> calcularLiquidacion(@PathVariable("id") Long id, @RequestBody LiquidacionInDTO liqInDTO) {
        return Optional.ofNullable(movimientoService.calcularLiquidacion(liqInDTO, id));

    }

    @PutMapping("/editarLiquidacion/{id}/")
    public Optional<Movimiento> editarLiquidacion(@PathVariable("id") Long id, @RequestBody DatosParaEditatLiq datosParaEditatLiq) {
        return Optional.ofNullable(movimientoService.editarLiquidacion(datosParaEditatLiq, id));

    }

    @DeleteMapping("/eliminarMovimiento/{id}/")
    public List<Movimiento> eliminarMovimiento(@PathVariable("id") Long id){
        return movimientoService.eliminarMovimiento(id);
    }

    @GetMapping("/obtener-config")
    public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String puerto){

        Map<String, String> json = new HashMap<>();
        json.put("texto", texto);
        json.put("puerto", puerto);

        if(env.getActiveProfiles().length>0 && env.getActiveProfiles()[0].equals("desarrollo")) {
            json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
            json.put("autor.email", env.getProperty("configuracion.autor.email"));

        }
        return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
    }

}

