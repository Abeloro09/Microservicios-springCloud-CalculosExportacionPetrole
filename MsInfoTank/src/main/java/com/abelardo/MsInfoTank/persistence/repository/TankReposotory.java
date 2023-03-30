package com.abelardo.MsInfoTank.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.abelardo.MsInfoTank.persistence.indentity.Tank;

public interface TankReposotory extends JpaRepository<Tank, Long> {
}
