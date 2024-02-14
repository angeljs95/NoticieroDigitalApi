package com.elobservador.noticiero.service;

import com.elobservador.noticiero.entidades.Usuario;
import com.elobservador.noticiero.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario getUser(String id) {
        return usuarioRepository.findById(id).orElse(null);

    }
    @Transactional
    public void cambiarEstado(String id) {
        Optional<Usuario> respuesta = usuarioRepository.findById(id);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();

            if (usuario.isActive() ) {
                usuario.setActive(false);
            } else if (!usuario.isActive()) {
                usuario.setActive(true);
            }
        }
    }

}
