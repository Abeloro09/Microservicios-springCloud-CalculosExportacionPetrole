package com.abelardo.MsLiquidacion.controller;

import com.abelardo.MsLiquidacion.persistence.entity.Movimiento;
import com.abelardo.MsLiquidacion.persistence.repository.MovimientoRepository;
import com.abelardo.MsLiquidacion.service.MovimientoService;
import com.abelardo.MsLiquidacion.service.dto.DatosParaEditatLiq;
import com.abelardo.MsLiquidacion.service.dto.LiquidacionInDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movimiento")
public class movimientoController {

    private final MovimientoService movimientoService;
    private final MovimientoRepository movimientoRepository;

    public movimientoController(MovimientoService movimientoService,
                                MovimientoRepository movimientoRepository) {
        this.movimientoService = movimientoService;
        this.movimientoRepository = movimientoRepository;
    }


    @PostMapping
    public Movimiento createMovimiento(){
        return this.movimientoService.createMovimiento();
    }

    @GetMapping
    public List<Movimiento> findAll(){
        return this.movimientoService.findAll();
    }

    @GetMapping("/byId/{id}")
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



}

