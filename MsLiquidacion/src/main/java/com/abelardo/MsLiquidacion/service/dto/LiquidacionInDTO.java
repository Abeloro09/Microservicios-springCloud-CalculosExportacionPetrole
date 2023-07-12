package com.abelardo.MsLiquidacion.service.dto;

import com.abelardo.MsLiquidacion.persistence.entity.Movimiento;
import com.abelardo.MsLiquidacion.persistence.entity.Quality;
import com.abelardo.MsLiquidacion.persistence.entity.Tank;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LiquidacionInDTO {

    @NotNull
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "20000", inclusive = false, message = "El valor debe ser menor que 20000")
    double gauge;
    @NotNull
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "1000000", inclusive = false, message = "El valor debe ser menor que 1000000.0")
    double tov;
    @NotNull
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "20000", inclusive = false, message = "El valor debe ser menor que 20000.0")
    double waterGauge;
    @NotNull
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "1000000", inclusive = false, message = "El valor debe ser menor que 1000000.0")
    double waterTov;
    @NotNull
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "100", inclusive = false, message = "El valor debe ser menor que 100.0")
    double tempL;
    @NotNull
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "100", inclusive = false, message = "El valor debe ser menor que 100.0")
    double tAmb;

    @NotNull
    Long  idQua;
    @NotNull
    Long  idTank;

}
