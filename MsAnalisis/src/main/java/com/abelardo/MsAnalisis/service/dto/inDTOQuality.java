package com.abelardo.MsAnalisis.service.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class InDTOQuality {
    @NotBlank
    private String descripcion;
    @NotBlank
    @Column(length = 50)
    private String referencia;
    @NotBlank
    @Column(length = 50)
    private String referencia_cliente;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "100.0", inclusive = false, message = "El valor debe ser menor que 100.0")
    private double api;
    @NotNull
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0")
    @DecimalMax(value = "2.0", inclusive = false, message = "El valor debe ser menor que 2.0")
    private double water;
    @NotNull
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0")
    @DecimalMax(value = "2.0", inclusive = false, message = "El valor debe ser menor que 2.0")
    private double sediment;

    private double salt;

    private double sulfur;

    private double tan;

    private double viscosity;
    private double flashpoint;

    String ABD;
}
