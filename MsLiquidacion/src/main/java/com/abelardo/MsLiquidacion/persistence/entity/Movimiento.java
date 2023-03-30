package com.abelardo.MsLiquidacion.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@Entity
@Table(name= "movimientos")
public class Movimiento {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id_movimiento")
    private Long id;

    private double difTOV;
    private double difFw;
    private double difGsv;
    private double difNsv;

    @OneToMany(mappedBy = "movimiento", cascade = CascadeType.ALL)
    private List<Liquidacion> listaLIquidaciones = new ArrayList<>();





}
