package com.abelardo.MsLiquidacion.controller;

import com.abelardo.MsLiquidacion.persistence.entity.Cargue;
import com.abelardo.MsLiquidacion.service.CargueService;
import com.abelardo.MsLiquidacion.service.dto.DatosEditarCargueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/cargue")
public class CargueController {

    @Autowired
    private CargueService cargueService;


    @PostMapping
    public Cargue crearCargue (){
        return  this.cargueService.createCargue();
    }

    @GetMapping("/byId/{id}")
    public Optional<Cargue> findById(Long cargueId){
        return this.cargueService.findById(cargueId);
    }

    @DeleteMapping("/eliminarCargue/{id}/")
    public List<Cargue> eliminarMovimiento(@PathVariable("id") Long id){
        return cargueService.eliminarCargue(id);
    }
// pendiente por revisar
    @PutMapping("/editarCargue/{id}/")
    public Cargue editarCargue(@PathVariable("id") Long id, @RequestBody DatosEditarCargueDTO datosEditarCargueDTO){



        return  this.cargueService.updateCargue(id, datosEditarCargueDTO);
    }

}
