package com.elobservador.noticiero.repositorio;

import com.elobservador.noticiero.entidades.Lector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectorRepository extends JpaRepository<Lector, String> {
}
