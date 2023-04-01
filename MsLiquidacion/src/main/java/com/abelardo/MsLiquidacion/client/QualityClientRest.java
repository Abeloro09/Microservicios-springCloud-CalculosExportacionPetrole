package com.abelardo.MsLiquidacion.client;

import com.abelardo.MsLiquidacion.persistence.entity.Quality;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "ms-analisis", url = "localhost:8082/analisis")
public interface QualityClientRest {

    @GetMapping
    public List<Quality> findAll();
    @GetMapping("/byId/{id}")
    public Optional<Quality> findById(@PathVariable("id") Long id);
}
