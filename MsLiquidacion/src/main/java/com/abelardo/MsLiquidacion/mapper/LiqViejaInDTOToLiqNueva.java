package com.abelardo.MsLiquidacion.mapper;

import com.abelardo.MsLiquidacion.persistence.entity.Liquidacion;
import com.abelardo.MsLiquidacion.service.MovimientoService;
import com.abelardo.MsLiquidacion.service.dto.DatosParaEditatLiq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// recibe un objeto de liquidacion sin el atributo movimiento
@Component
public class LiqViejaInDTOToLiqNueva implements IMapper<DatosParaEditatLiq, Liquidacion>{

    @Autowired
    MovimientoService movimiento;
    @Override
    public Liquidacion map(DatosParaEditatLiq in) {

        Liquidacion nuevaLiquidacion = new Liquidacion();

        nuevaLiquidacion.setABD(in.getABD());
        nuevaLiquidacion.setGauge(in.getGauge());
        nuevaLiquidacion.setTov(in.getTov());
        nuevaLiquidacion.setWaterGauge(in.getWaterGauge());
        nuevaLiquidacion.setWaterTov(in.getWaterTov());
        nuevaLiquidacion.setKFra1(in.getKFra1());
        nuevaLiquidacion.setKFra2(in.getKFra2());
        nuevaLiquidacion.setTLam(in.getTLam());
        nuevaLiquidacion.setTempL(in.getTempL());
        nuevaLiquidacion.setTAmb(in.getTAmb());
        nuevaLiquidacion.setApi60(in.getApi60());
        nuevaLiquidacion.setBsw(in.getBsw());
        nuevaLiquidacion.setNombreTk(in.getNombreTk());


        nuevaLiquidacion.setFra(in.getFra());
        nuevaLiquidacion.setCtsh(in.getCtsh());
        nuevaLiquidacion.setGov(in.getGov());
        nuevaLiquidacion.setCtl(in.getCtl());
        nuevaLiquidacion.setGsv(in.getGsv());
        nuevaLiquidacion.setNsv(in.getNsv());

        return nuevaLiquidacion;
    }
}
