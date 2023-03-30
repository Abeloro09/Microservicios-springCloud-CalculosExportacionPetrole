package com.abelardo.MsLiquidacion.service;

import com.abelardo.MsLiquidacion.mapper.LiqViejaInDTOToLiqNueva;
import com.abelardo.MsLiquidacion.mapper.LiquidacionInDTOToLiquidacion;
import com.abelardo.MsLiquidacion.persistence.entity.Liquidacion;
import com.abelardo.MsLiquidacion.persistence.entity.Movimiento;
import com.abelardo.MsLiquidacion.persistence.repository.MovimientoRepository;
import com.abelardo.MsLiquidacion.service.dto.DatosParaEditatLiq;
import com.abelardo.MsLiquidacion.service.dto.LiquidacionInDTO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Data
@Service
public class MovimientoService {
    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private final LiquidacionService liquidacionService;
    @Autowired
    private  LiquidacionInDTOToLiquidacion mapper;

    @Autowired
    private LiqViejaInDTOToLiqNueva mapper2;


    public Movimiento createMovimiento(){

        //CREO LA LISTA DE LIQUIDACION VACIA
        List<Liquidacion> listaLiquidaciones = new ArrayList<>();


        //CREO EL MOVIMIENTO CON LA LISTA DE LIQUIDACION VACIA
        Movimiento movimiento = new Movimiento();

        //GUARDO EL MOVIMIENTO EN BASE DE DATOS
        movimientoRepository.save(movimiento);

         // CREO Y GUARDO LAS LIQUIDACIONES EN BASE DE DATOS
        Liquidacion liquidacionInicial = liquidacionService.crearLiqInicial(movimiento);
        Liquidacion liquidacionFinal = liquidacionService.crearLiqInicial(movimiento);

        //AGREGO LAS LIQUIDACIONES A LA LISTA

        listaLiquidaciones.add(liquidacionInicial);
        listaLiquidaciones.add(liquidacionFinal);

         movimiento.setListaLIquidaciones(listaLiquidaciones);
         movimiento.setDifTOV(0);
         movimiento.setDifFw(0);
         movimiento.setDifGsv(0);
         movimiento.setDifNsv(0);

         movimientoRepository.save(movimiento);

        return movimiento;

    }

    public List<Movimiento> findAll(){
        return  movimientoRepository.findAll();
    }

    public Optional<Movimiento> findById(Long id){
        return movimientoRepository.findById(id);
    }

    public Movimiento updateMovimiento(Movimiento movimiento){ return this.movimientoRepository.save(movimiento);
    }


// Solo calcula una liquidacion sin guardar
    public Movimiento calcularLiquidacion (LiquidacionInDTO in, Long id){

        Optional<Liquidacion> liquidacionAcambiar = liquidacionService.findById(id);
        Movimiento movimiento = liquidacionAcambiar.get().getMovimiento();

        Liquidacion liquidacionNueva = liquidacionAcambiar.get();


        //Instancio un objeto de la clase que me permite hacer los calculos
        //CalculationsLiq calculationsLiq = new CalculationsLiq(in.getABD(), in.getGauge(), in.getTov(), in.getWaterGauge(), in.getWaterTov(), in.getKFra1(), in.getKFra2(), in.getTLam(), in.getTempL(), in.getTAmb(), in.getApi60(), in.getBsw());

        //transformo los datos de entrada necesarios de una liquidacion en una liquidacion completa
        Liquidacion liquidacioATomar = mapper.map(in);

        //actualizo los valores de la liquidacion inicial con la funcion actualizar valores
        Liquidacion liquidacionActualizada = actualizarValoresLiq(liquidacioATomar, liquidacionNueva, movimiento);

        //Guardo la liquidacion en base de datos
        liquidacionService.updateLiquidacion(liquidacionActualizada);

        // Calculo las diferencias entre movimientos
        Movimiento movimientoCalculado = calcularDiferencias(movimiento);

        //guardo el movimiento en base de datos
        movimientoRepository.save(movimientoCalculado);



        return movimiento;

     }

// solo edita una liquidacion sin guardar
    public Movimiento editarLiquidacion(DatosParaEditatLiq datosParaEditatLiq, Long id){

        Optional<Liquidacion> liquidacionAcambiar = liquidacionService.findById(id);
        Movimiento movimiento = liquidacionAcambiar.get().getMovimiento();

        Liquidacion liquidacionNueva = liquidacionAcambiar.get();

        //cree una clase liquidacionVieja que no contenga el atributo movimiento (porque si lo contiene lo solicita y genera error a la hora de guardarlo en la base de datos con el valor null)
        Liquidacion liquidacioATomar = mapper2.map(datosParaEditatLiq);

        //actualizo los valores de la liquidacion inicial con la funcion actualizar valores
        Liquidacion liquidacionActualizada = actualizarValoresLiq(liquidacioATomar, liquidacionNueva, movimiento);

        //Guardo la liquidacion en base de datos
        liquidacionService.updateLiquidacion(liquidacionActualizada);

        // Calculo las diferencias entre movimientos
        Movimiento movimientoCalculado = calcularDiferencias(movimiento);

        //guardo el movimiento en base de datos
        movimientoRepository.save(movimientoCalculado);


        return movimiento;



    }

    public Movimiento calcularDiferencias(Movimiento movimiento){

        double tovIni = movimiento.getListaLIquidaciones().get(0).getTov();
        double tovFin = movimiento.getListaLIquidaciones().get(1).getTov();
        movimiento.setDifTOV(tovIni-tovFin);

        double fwIni = movimiento.getListaLIquidaciones().get(0).getWaterTov();
        double fwvFin = movimiento.getListaLIquidaciones().get(1).getWaterTov();
        movimiento.setDifFw(fwIni-fwvFin);

        double gsvIni = movimiento.getListaLIquidaciones().get(0).getGsv();
        double gsvFin = movimiento.getListaLIquidaciones().get(1).getGsv();
        movimiento.setDifGsv(gsvIni-gsvFin);

        double nsvIni = movimiento.getListaLIquidaciones().get(0).getNsv();
        double nsvFin = movimiento.getListaLIquidaciones().get(1).getNsv();
        movimiento.setDifNsv(nsvIni-nsvFin);

        return movimiento;

    }

    public Liquidacion actualizarValoresLiq (Liquidacion liquidacioATomar, Liquidacion liquidacionNueva, Movimiento movimiento){

        liquidacionNueva.setABD(liquidacioATomar.getABD());
        liquidacionNueva.setGauge(liquidacioATomar.getGauge());
        liquidacionNueva.setTov(liquidacioATomar.getTov());
        liquidacionNueva.setWaterGauge(liquidacioATomar.getWaterGauge());
        liquidacionNueva.setWaterTov(liquidacioATomar.getWaterTov());
        liquidacionNueva.setKFra1(liquidacioATomar.getKFra1());
        liquidacionNueva.setKFra2(liquidacioATomar.getKFra2());
        liquidacionNueva.setTLam(liquidacioATomar.getTLam());
        liquidacionNueva.setTempL(liquidacioATomar.getTempL());
        liquidacionNueva.setTAmb(liquidacioATomar.getTAmb());
        liquidacionNueva.setApi60(liquidacioATomar.getApi60());
        liquidacionNueva.setBsw(liquidacioATomar.getBsw());
        liquidacionNueva.setNombreTk(liquidacioATomar.getNombreTk());
        liquidacionNueva.setMovimiento(movimiento);

        liquidacionNueva.setFra(liquidacioATomar.getFra());
        liquidacionNueva.setCtsh(liquidacioATomar.getCtsh());
        liquidacionNueva.setGov(liquidacioATomar.getGov());
        liquidacionNueva.setCtl(liquidacioATomar.getCtl());
        liquidacionNueva.setGsv(liquidacioATomar.getGsv());
        liquidacionNueva.setNsv(liquidacioATomar.getNsv());

        return liquidacionNueva;

    }

    public List<Movimiento> eliminarMovimiento(Long id){

        movimientoRepository.deleteById(id);

        return  movimientoRepository.findAll();
    }

}
