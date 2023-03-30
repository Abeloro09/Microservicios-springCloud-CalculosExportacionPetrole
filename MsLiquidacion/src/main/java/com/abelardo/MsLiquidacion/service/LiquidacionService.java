package com.abelardo.MsLiquidacion.service;

import com.abelardo.MsLiquidacion.mapper.LiquidacionInDTOToLiquidacion;
import com.abelardo.MsLiquidacion.persistence.entity.Liquidacion;
import com.abelardo.MsLiquidacion.persistence.entity.Movimiento;
import com.abelardo.MsLiquidacion.persistence.repository.LiquidacionRepository;
import com.abelardo.MsLiquidacion.service.dto.LiquidacionInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LiquidacionService {


    @Autowired
    private  LiquidacionInDTOToLiquidacion mapper;
    @Autowired
    private  Liquidacion liquidacion;

    @Autowired
    private  LiquidacionRepository liquidacionRepository;

    @Autowired
    private MovimientoService movimiento;



    public Liquidacion createLiquidacion(LiquidacionInDTO liquidacionInDTO){
        Liquidacion liquidacion = mapper.map(liquidacionInDTO);
        liquidacionRepository.save(liquidacion);
        return liquidacion;
    }


    public Liquidacion crearLiqInicial(Movimiento movimiento){
        Liquidacion liquidacionInicial = new Liquidacion();
        liquidacionInicial.setABD("A");
        liquidacionInicial.setGauge(0);
        liquidacionInicial.setTov(0);
        liquidacionInicial.setWaterGauge(0);
        liquidacionInicial.setWaterTov(0);
        liquidacionInicial.setKFra1(0);
        liquidacionInicial.setKFra2(0);
        liquidacionInicial.setTLam(0);
        liquidacionInicial.setTempL(0);
        liquidacionInicial.setTAmb(0);
        liquidacionInicial.setApi60(0);
        liquidacionInicial.setBsw(0);

        liquidacionInicial.setFra(0);
        liquidacionInicial.setCtsh(0);
        liquidacionInicial.setGov(0);
        liquidacionInicial.setCtl(0);
        liquidacionInicial.setGsv(0);
        liquidacionInicial.setNsv(0);
        liquidacionInicial.setMovimiento(movimiento);


        return liquidacionRepository.save(liquidacionInicial);

    }

    public Liquidacion liquidar (LiquidacionInDTO liquidacionInDTO){
        Liquidacion liquidacion = mapper.map(liquidacionInDTO);
        return  liquidacion;
    }

    public List<Liquidacion> findAll(){ return this.liquidacionRepository.findAll();}

    public Optional<Liquidacion> findById(Long id){
        return liquidacionRepository.findById(id);
    }

    public Liquidacion updateLiquidacion(Liquidacion liquidacion){
        return this.liquidacionRepository.save(liquidacion);
    }

    public List<Liquidacion> delete(Long id){
        liquidacionRepository.deleteById(id);
        return this.liquidacionRepository.findAll();

    }



}
