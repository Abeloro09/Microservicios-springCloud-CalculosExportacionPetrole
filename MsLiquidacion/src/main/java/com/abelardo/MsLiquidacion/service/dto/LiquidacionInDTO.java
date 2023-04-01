package com.abelardo.MsLiquidacion.service.dto;

import com.abelardo.MsLiquidacion.persistence.entity.Movimiento;
import com.abelardo.MsLiquidacion.persistence.entity.Quality;
import com.abelardo.MsLiquidacion.persistence.entity.Tank;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class LiquidacionInDTO {

    //String nombreTk; Quality
    //String ABD;
    double gauge;
    double tov;
    double waterGauge;
    double waterTov;
    //double kFra1; Tank
    //double kFra2; Tank
    //double tLam; Tank
    double tempL;
    double tAmb;
    //double api60; Quality
    //double bsw;  Quality

    Long  idQua;
    Long  idTank;

}
