package com.abelardo.MsLiquidacion.service.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class DatosEditarCargueDTO {
    @NotBlank
    private String referencia;
    @NotBlank
    private String referenciaCliente;
    @NotBlank
    private String comprador;
    @NotBlank
    private String vendedor;
    @NotBlank
    private String destino;
    @NotBlank
    private String inspector;
    @NotBlank
    private String terminal;
    @NotBlank
    private String nombreBuque;
}
