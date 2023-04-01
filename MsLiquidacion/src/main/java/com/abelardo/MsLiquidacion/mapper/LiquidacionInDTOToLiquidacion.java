package com.abelardo.MsLiquidacion.mapper;

import com.abelardo.MsLiquidacion.client.QualityClientRest;
import com.abelardo.MsLiquidacion.client.TankClientRest;
import com.abelardo.MsLiquidacion.persistence.entity.Liquidacion;
import com.abelardo.MsLiquidacion.persistence.entity.Movimiento;
import com.abelardo.MsLiquidacion.persistence.entity.Quality;
import com.abelardo.MsLiquidacion.persistence.entity.Tank;
import com.abelardo.MsLiquidacion.service.MovimientoService;
import com.abelardo.MsLiquidacion.service.dto.LiquidacionInDTO;
import com.abelardo.MsLiquidacion.util.CalculationsLiq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LiquidacionInDTOToLiquidacion implements IMapper<LiquidacionInDTO, Liquidacion>{


    @Autowired
    private QualityClientRest qualityClientRest;

    @Autowired
    private TankClientRest tankClientRest;


    @Override
    public Liquidacion map(LiquidacionInDTO in) {

        Optional<Quality> quality = qualityClientRest.findById(in.getIdQua());
        Optional<Tank> tank = tankClientRest.findById(in.getIdTank());

        //instancio un objeto de la clase que debo guardar en la base de datos
        Liquidacion liquidacion = new Liquidacion();



        //Instancio un objeto de la clase que me permite hacer los calculos
        CalculationsLiq calculationsLiq = new CalculationsLiq(quality.get().getABD(), in.getGauge(), in.getTov(), in.getWaterGauge(), in.getWaterTov(), tank.get().getFra1(),  tank.get().getFra2(), tank.get().getTempLamina(), in.getTempL(), in.getTAmb(), quality.get().getApi(), quality.get().getWater()+quality.get().getSediment());

        liquidacion.setABD(quality.get().getABD());
        liquidacion.setGauge(in.getGauge());
        liquidacion.setTov(in.getTov());
        liquidacion.setWaterGauge(in.getWaterGauge());
        liquidacion.setWaterTov(in.getWaterTov());
        liquidacion.setKFra1(tank.get().getFra1());
        liquidacion.setKFra2(tank.get().getFra2());
        liquidacion.setTLam(tank.get().getTempLamina());
        liquidacion.setTempL(in.getTempL());
        liquidacion.setTAmb(in.getTAmb());
        liquidacion.setApi60(quality.get().getApi());
        liquidacion.setBsw(quality.get().getWater()+quality.get().getSediment());
        liquidacion.setNombreTk(tank.get().getNombreTk());


        liquidacion.setFra(calculationsLiq.fra());
        liquidacion.setCtsh(calculationsLiq.ctsh());
        liquidacion.setGov(calculationsLiq.gov());
        liquidacion.setCtl(calculationsLiq.ctl());
        liquidacion.setGsv(calculationsLiq.gsv());
        liquidacion.setNsv(calculationsLiq.nsv());


        return liquidacion;

    }


}
