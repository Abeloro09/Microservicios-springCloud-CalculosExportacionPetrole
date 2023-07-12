package com.abelardo.MsAnalisis.controller;

import com.abelardo.MsAnalisis.mapper.InDTOToQuality;
import com.abelardo.MsAnalisis.mapper.OutDTOToQuality;
import com.abelardo.MsAnalisis.persistence.identity.Quality;
import com.abelardo.MsAnalisis.persistence.repository.QualityRepository;
import com.abelardo.MsAnalisis.service.QualityService;
import com.abelardo.MsAnalisis.service.dto.InDTOQuality;
import com.abelardo.MsAnalisis.service.dto.OutDTOQuality;
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
import java.util.concurrent.TimeUnit;

@RefreshScope
@RestController
@RequestMapping("/quality")
public class QualityController {
    @Autowired
    private Environment env;
    @Value("${configuracion.texto}") private String texto;

    private final QualityService qualityService;
    private final QualityRepository qualityRepository;

    private final OutDTOToQuality outDTOToQuality;



    public QualityController(QualityService qualityService, QualityRepository qualityRepository, OutDTOToQuality outDTOToQuality){
        this.qualityService=qualityService;
        this.qualityRepository=qualityRepository;
        this.outDTOToQuality=outDTOToQuality;


    }

    @PostMapping
    public ResponseEntity <Quality> createQuality(@RequestBody @Valid InDTOQuality inDTOQuality, UriComponentsBuilder uriComponentsBuilder){

        Quality quality = this.qualityService.createQuality(inDTOQuality);
        URI url = uriComponentsBuilder.path("/quality/{id}").buildAndExpand(quality.getId()).toUri();
        return ResponseEntity.created(url).body(quality);
    }


    @GetMapping("/byId/{id}")
    public ResponseEntity<Optional<Quality>> findById(@PathVariable("id") Long id) throws InterruptedException {

        Optional<Quality> qualityOptional = this.qualityService.findById(id);

        if (qualityOptional.isPresent()) {

            //Para probar falla
            if (id.equals(10L)){
                throw new IllegalStateException("Calidad No Encontrada");
            }
            //para probar timeOut
            if (id.equals(7L)){
                TimeUnit.SECONDS.sleep(3L);
            }

            return ResponseEntity.ok(qualityOptional);

        } else{
            String mensaje = "No se encuentra el id: ";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    }

    @GetMapping("/result") //
    public ResponseEntity<Page<OutDTOQuality>> qualityList(@PageableDefault(size = 2) Pageable paginacion) {

       return ResponseEntity.ok(qualityService.findall(paginacion).map(outDTOToQuality::map));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity <Quality> updateQuality(@PathVariable("id") Long id, @Valid InDTOQuality inDTOQuality){
        Quality updatedquality = this.qualityService.findById(id).get();

        updatedquality.setDescripcion(inDTOQuality.getDescripcion());
        updatedquality.setApi(inDTOQuality.getApi());
        updatedquality.setWater(inDTOQuality.getWater());
        updatedquality.setSediment(inDTOQuality.getSediment());
        updatedquality.setSalt(inDTOQuality.getSalt());
        updatedquality.setSalt(inDTOQuality.getSalt());
        updatedquality.setSulfur(inDTOQuality.getSulfur());
        updatedquality.setViscosity(inDTOQuality.getViscosity());
        updatedquality.setViscosity(inDTOQuality.getViscosity());

        qualityService.updateQuality(updatedquality);

        return ResponseEntity.ok(updatedquality);

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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<Quality>> deleteTank(@PathVariable("id") Long id){
        qualityService.deleteQuality(id);

        return ResponseEntity.ok(this.qualityService.findall());

    }


}
