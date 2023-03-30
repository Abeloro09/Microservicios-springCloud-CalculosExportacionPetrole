package com.abelardo.MsLiquidacion.persistence.repository;

import com.abelardo.MsLiquidacion.persistence.entity.Movimiento;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
}
