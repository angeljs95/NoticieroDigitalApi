package com.elobservador.noticiero.service;

import com.elobservador.noticiero.entidades.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario createUsuario(Usuario usuario);

    Usuario getUsuario( String id);
    void updateUsuario(Usuario usuario);

    void deleteUsuario( String id);

    List<Usuario> listUsuarios();




}
