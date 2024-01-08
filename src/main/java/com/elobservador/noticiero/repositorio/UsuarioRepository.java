package com.elobservador.noticiero.repositorio;

import com.elobservador.noticiero.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,String> {
}
