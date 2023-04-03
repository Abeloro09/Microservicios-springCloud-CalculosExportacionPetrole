package com.abelardo.MsLiquidacion.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name= "cargues")
public class Cargue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cargue")
    private Long id;

    @Column(name = "referencias", length = 20, unique = true)
    private String referencia;
    @Column(name = "referencias_clientes", length = 20, unique = true)
    private String referenciaCliente;
    @Column(name = "compradores", length = 50)
    private String comprador;
    @Column(name = "vendedores", length = 50)
    private String vendedor;
    @Column(name = "destinos", length = 50)
    private String destino;
    @Column(name = "inspectores", length = 50)
    private String inspector;
    @Column(name = "terminales", length = 50)
    private String terminal;
    @Column(name = "nombres_buques", length = 50)
    private String nombreBuque;

    @OneToMany(mappedBy = "cargue", cascade = CascadeType.ALL)
    private List<Movimiento> listaMovimientos = new ArrayList<>();

}
