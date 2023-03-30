package com.abelardo.MsLiquidacion.service.dto;

import com.abelardo.MsLiquidacion.persistence.entity.Movimiento;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class LiquidacionInDTO {

    String nombreTk;
    String ABD;
    double gauge;
    double tov;
    double waterGauge;
    double waterTov;
    double kFra1;
    double kFra2;
    double tLam;
    double tempL;
    double tAmb;
    double api60;
    double bsw;

}
