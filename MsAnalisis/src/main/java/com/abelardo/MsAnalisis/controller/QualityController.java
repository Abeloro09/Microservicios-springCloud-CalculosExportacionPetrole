package com.abelardo.MsAnalisis.controller;

import com.abelardo.MsAnalisis.persistence.identity.Quality;
import com.abelardo.MsAnalisis.service.QualityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/analisis")
public class QualityController {

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
    public Optional<Quality> findById(@PathVariable("id") Long id){
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


}
