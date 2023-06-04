package com.abelardo.MsAnalisis.service.dto;

import lombok.Data;

@Data
public class inDTOQuality {
    private String descripcion;
    private double api;
    private double water;
    private double sediment;
    private double salt;
    private double sulfur;
    private double tan;
    private double viscosity;
    private double flashpoint;

    String ABD;
}
