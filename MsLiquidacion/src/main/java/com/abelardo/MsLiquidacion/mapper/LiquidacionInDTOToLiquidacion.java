package com.abelardo.MsLiquidacion.mapper;

import com.abelardo.MsLiquidacion.persistence.entity.Liquidacion;
import com.abelardo.MsLiquidacion.persistence.entity.Movimiento;
import com.abelardo.MsLiquidacion.service.MovimientoService;
import com.abelardo.MsLiquidacion.service.dto.LiquidacionInDTO;
import com.abelardo.MsLiquidacion.util.CalculationsLiq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LiquidacionInDTOToLiquidacion implements IMapper<LiquidacionInDTO, Liquidacion>{

    //@Autowired
    //MovimientoService movimiento;


    @Override
    public Liquidacion map(LiquidacionInDTO in) {
        //instancio un objeto de la clase que debo guardar en la base de datos
        Liquidacion liquidacion = new Liquidacion();



        //Instancio un objeto de la clase que me permite hacer los calculos
        CalculationsLiq calculationsLiq = new CalculationsLiq(in.getABD(), in.getGauge(), in.getTov(), in.getWaterGauge(), in.getWaterTov(), in.getTank().getFra1(), in.getTank().getFra2(), in.getTank().getTempLamina(), in.getTempL(), in.getTAmb(), in.getQuality().getApi(), in.getQuality().getWater()+in.getQuality().getSediment());

        liquidacion.setABD(in.getABD());
        liquidacion.setGauge(in.getGauge());
        liquidacion.setTov(in.getTov());
        liquidacion.setWaterGauge(in.getWaterGauge());
        liquidacion.setWaterTov(in.getWaterTov());
        liquidacion.setKFra1(in.getTank().getFra1());
        liquidacion.setKFra2(in.getTank().getFra2());
        liquidacion.setTLam(in.getTank().getTempLamina());
        liquidacion.setTempL(in.getTempL());
        liquidacion.setTAmb(in.getTAmb());
        liquidacion.setApi60(in.getQuality().getApi());
        liquidacion.setBsw(in.getQuality().getWater()+in.getQuality().getSediment());
        liquidacion.setNombreTk(in.getTank().getNombreTk());


        liquidacion.setFra(calculationsLiq.fra());
        liquidacion.setCtsh(calculationsLiq.ctsh());
        liquidacion.setGov(calculationsLiq.gov());
        liquidacion.setCtl(calculationsLiq.ctl());
        liquidacion.setGsv(calculationsLiq.gsv());
        liquidacion.setNsv(calculationsLiq.nsv());


        return liquidacion;

    }


}