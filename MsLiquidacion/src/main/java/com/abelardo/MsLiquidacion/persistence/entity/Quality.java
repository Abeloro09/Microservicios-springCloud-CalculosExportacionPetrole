package com.abelardo.MsLiquidacion.persistence.entity;

import lombok.Data;

import javax.persistence.Entity;

@Data
public class Quality {
    private Long id;
    private String descripcion;
    private double api;
    private double water;
    private double sediment;
    private double salt;
    private double sulfur;
    private double tan;
    private double viscosity;
    private double flashpoint;

}
