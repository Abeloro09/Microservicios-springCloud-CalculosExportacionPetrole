package com.abelardo.MsLiquidacion.controller;

import com.abelardo.MsLiquidacion.client.QualityClientRest;
import com.abelardo.MsLiquidacion.persistence.entity.Movimiento;
import com.abelardo.MsLiquidacion.persistence.entity.Quality;
import com.abelardo.MsLiquidacion.persistence.repository.MovimientoRepository;
import com.abelardo.MsLiquidacion.service.MovimientoService;
import com.abelardo.MsLiquidacion.service.dto.DatosParaEditatLiq;
import com.abelardo.MsLiquidacion.service.dto.LiquidacionInDTO;
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
@RequestMapping("/movimiento")
public class movimientoController {



    private final QualityClientRest qualityClientRest;

    private final MovimientoService movimientoService;
    private final MovimientoRepository movimientoRepository;
    @Value("${configuracion.texto}") private String texto;
    @Autowired
    private Environment env;


    public movimientoController(MovimientoService movimientoService,
                                MovimientoRepository movimientoRepository,
                                QualityClientRest qualityClientRest) {

        this.movimientoService = movimientoService;
        this.movimientoRepository = movimientoRepository;
        this.qualityClientRest = qualityClientRest;
    }


    @PostMapping("/crearmovimiento/{cargueId}")
    public ResponseEntity <Movimiento> createMovimiento(@PathVariable("cargueId") Long cargueId, UriComponentsBuilder uriComponentsBuilder){
        Movimiento movimiento = this.movimientoService.createMovimiento(cargueId);
        URI url = uriComponentsBuilder.path("/movimiento/{id}").buildAndExpand(movimiento.getId()).toUri();
        return ResponseEntity.ok(movimiento);
    }

    @GetMapping("/movimientos")
    public ResponseEntity <Page<Movimiento>> findAll(@PageableDefault(size = 2) Pageable paginacion){

        return ResponseEntity.ok(this.movimientoService.findAll(paginacion));
    }


    @GetMapping("/movimientoById/{id}")
    public ResponseEntity<Optional<Movimiento>>findById(@PathVariable("id") Long id){
        Optional<Movimiento> movimientoOptional = this.movimientoService.findById(id);

        if (movimientoOptional.isPresent()){
            return ResponseEntity.ok(movimientoOptional);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }


    @PutMapping("/calcularLiquidacion/{id}/")
    public ResponseEntity<Optional<Movimiento>> calcularLiquidacion(@PathVariable("id") Long id,
                                                                    @RequestBody @Valid LiquidacionInDTO liquidacionInDTO) {
        return ResponseEntity.ok(Optional.ofNullable(movimientoService.calcularLiquidacion(liquidacionInDTO, id)));

    }

    @PutMapping("/editarLiquidacion/{id}/")
    public ResponseEntity<Optional<Movimiento>> editarLiquidacion(@PathVariable("id") Long id,
                                                                  @RequestBody @Valid DatosParaEditatLiq datosParaEditatLiq) {
        return ResponseEntity.ok(Optional.ofNullable(movimientoService.editarLiquidacion(datosParaEditatLiq, id)));

    }

    @DeleteMapping("/eliminarMovimiento/{id}/")
    public ResponseEntity eliminarMovimiento(@PathVariable("id") Long id){
        movimientoService.eliminarMovimiento(id);
        return  ResponseEntity.noContent().build();
    }

    //Probando obtener configuracion del servidor de configuraciones
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

