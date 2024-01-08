package com.elobservador.noticiero.repositorio;

import com.elobservador.noticiero.entidades.Periodista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodistaRepository extends JpaRepository<Periodista,String> {
}
