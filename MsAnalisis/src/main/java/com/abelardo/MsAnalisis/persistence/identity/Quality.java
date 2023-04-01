package com.abelardo.MsAnalisis.persistence.identity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="qualities")
public class Quality implements Serializable {

    private static final long serialVersionUID = 1234567891234567895L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    String ABD;




}
