package com.abelardo.MsInfoTank.persistence.indentity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tanks")
public class Tank implements Serializable {

    private static final long serialVersionUID = 1234567891234567894L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NOMBRETK")
    private String nombreTk;
    @Column(name = "CAPACIDADNOMINAL")
    private double capacidadNominal;
    @Column(name = "INICIOZONACRITICA")
    private int inicioZonaCritica;
    @Column(name = "FINALZONACRITICA")
    private int finalZonaCritica;
    @Column(name = "TEMPLAMINA")
    private double tempLamina;
    @Column(name = "FRA1")
    private double fra1;
    @Column(name = "FRA2")
    private double fra2;


}
