package com.abelardo.MsLiquidacion.client;

import com.abelardo.MsLiquidacion.persistence.entity.Quality;
import com.abelardo.MsLiquidacion.persistence.entity.Tank;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "ms-infotank")
public interface TankClientRest {

    @GetMapping
    public List<Tank> findAll();

    //@GetMapping("/infotank/byId/{id}")
    @GetMapping("/tank/byId/{id}")
    public Optional<Tank> findById(@PathVariable("id") Long id);
}
