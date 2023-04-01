package com.abelardo.MsLiquidacion.persistence.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Component
@Data
@Entity
@Table(name="liquidaciones")
public class Liquidacion {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_liquidacion")
    private Long id;
    String ABD;
    double gauge;
    double tov;
    double waterGauge;
    double waterTov;
    double kFra1;
    double kFra2;
    double fra;
    double tLam;
    double tempL;
    double tAmb;
    double ctsh;
    double gov;
    double api60;
    double ctl;
    double gsv;
    double bsw;
    double nsv;
    String nombreTk;

    //private Tank tank;

    //private Quality quality;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_movimiento")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Movimiento movimiento;


}
