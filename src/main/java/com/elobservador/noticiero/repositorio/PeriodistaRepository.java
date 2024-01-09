package com.elobservador.noticiero.repositorio;

import com.elobservador.noticiero.entidades.Periodista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodistaRepository extends JpaRepository<Periodista,String> {
}
