package com.abelardo.MsLiquidacion.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cargue")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Cargue cargue;





}
