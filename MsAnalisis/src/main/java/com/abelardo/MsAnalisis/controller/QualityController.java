package com.abelardo.MsAnalisis.controller;

import com.abelardo.MsAnalisis.persistence.identity.Quality;
import com.abelardo.MsAnalisis.service.QualityService;
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
import java.util.concurrent.TimeUnit;

@RefreshScope
@RestController
public class QualityController {
    @Autowired
    private Environment env;
    @Value("${configuracion.texto}") private String texto;

    private final QualityService qualityService;

    public QualityController(QualityService qualityService){
        this.qualityService=qualityService;
    }

    @PostMapping
    public Quality createQuality(@RequestBody Quality quality){
        return this.qualityService.createQuality(quality);
    }

    @GetMapping
    public List<Quality> findAll(){
        return this.qualityService.findall();
    }

    @GetMapping("/byId/{id}")
    public Optional<Quality> findById(@PathVariable("id") Long id) throws InterruptedException {

        //Para probar falla
        if (id.equals(10L)){
            throw new IllegalStateException("Calidad No Encontrada");
        }
        //para probar timeOut
        if (id.equals(7L)){
            TimeUnit.SECONDS.sleep(3L);
        }
        return this.qualityService.findById(id);
    }

    @PutMapping("/update/{id}")
    public Quality updateQuality(@PathVariable("id") Long id, @RequestBody Quality quality){
        Quality updatedquality = this.qualityService.findById(id).get();

        updatedquality.setDescripcion(quality.getDescripcion());
        updatedquality.setApi(quality.getApi());
        updatedquality.setWater(quality.getWater());
        updatedquality.setSediment(quality.getSediment());
        updatedquality.setSalt(quality.getSalt());
        updatedquality.setSalt(quality.getSalt());
        updatedquality.setSulfur(quality.getSulfur());
        updatedquality.setViscosity(quality.getViscosity());
        updatedquality.setViscosity(quality.getViscosity());

        qualityService.updateQuality(updatedquality);

        return updatedquality;

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
    public List<Quality> deleteTank(@PathVariable("id") Long id){
        qualityService.deleteQuality(id);
        return this.qualityService.findall();

    }


}
