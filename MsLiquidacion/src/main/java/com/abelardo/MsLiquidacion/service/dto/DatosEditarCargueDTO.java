package com.abelardo.MsLiquidacion.service.dto;

import lombok.Data;

import javax.persistence.Column;
@Data
public class DatosEditarCargueDTO {

    private String referencia;

    private String referenciaCliente;

    private String comprador;

    private String vendedor;

    private String destino;

    private String inspector;

    private String terminal;

    private String nombreBuque;
}
