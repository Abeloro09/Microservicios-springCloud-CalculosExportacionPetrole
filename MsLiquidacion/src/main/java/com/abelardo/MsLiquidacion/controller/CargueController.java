package com.abelardo.MsLiquidacion.controller;

import com.abelardo.MsLiquidacion.persistence.entity.Cargue;
import com.abelardo.MsLiquidacion.service.CargueService;
import com.abelardo.MsLiquidacion.service.dto.DatosEditarCargueDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/cargue")
public class CargueController {


    public CargueController(CargueService cargueService) {
        this.cargueService = cargueService;
    }

    private CargueService cargueService;


    @PostMapping
    public ResponseEntity <Cargue> crearCargue (UriComponentsBuilder uriComponentsBuilder){
        Cargue cargue = this.cargueService.createCargue();
        URI url = uriComponentsBuilder.path("/cargue/{id}").buildAndExpand(cargue.getId()).toUri();
        return  ResponseEntity.created(url).body(cargue);
    }

    @GetMapping("/cargueById/{cargueId}")
    public ResponseEntity<Optional<Cargue>> findById(@PathVariable("cargueId") Long cargueId){
        Optional <Cargue> cargueOptional = this.cargueService.findById(cargueId);

        if (cargueOptional.isPresent()){
            return ResponseEntity.ok(cargueOptional);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    }

    @GetMapping("/cargues")
    public  ResponseEntity<Page<Cargue>> findAll(@PageableDefault(size = 2) Pageable paginacion){

        return ResponseEntity.ok(this.cargueService.findAll(paginacion));
    }

    @DeleteMapping("/eliminarCargue/{id}/")
    public ResponseEntity eliminarMovimiento(@PathVariable("id") Long id){
        cargueService.eliminarCargue(id);
       return ResponseEntity.noContent().build();
    }

    // pendiente por revisar
    @PutMapping("/editarCargue/{id}/")
    public ResponseEntity<Cargue> editarCargue(@PathVariable("id") Long id, @RequestBody @Valid DatosEditarCargueDTO datosEditarCargueDTO){

        return  ResponseEntity.ok(this.cargueService.updateCargue(id, datosEditarCargueDTO));
    }

}
