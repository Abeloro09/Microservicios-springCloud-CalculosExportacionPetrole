package com.abelardo.MsLiquidacion.service.dto;


import lombok.Data;

@Data
public class OutDTOCargue {

    private Long id;
    private String referencia;
    private String referenciaCliente;
    private String comprador;
    private String vendedor;
    private String nombreBuque;

}
