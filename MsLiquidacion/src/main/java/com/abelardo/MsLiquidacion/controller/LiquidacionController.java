package com.abelardo.MsLiquidacion.controller;



import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/liquidacion")
public class LiquidacionController {

// No expongo ningun Endpoint para esta entidad porque no se debe crear ninguna liquidacion sin haber cread0 un movimiento, se crean al crear un movimiento.



}
