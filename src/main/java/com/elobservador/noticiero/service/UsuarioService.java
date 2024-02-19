package com.elobservador.noticiero.service;

import com.elobservador.noticiero.entidades.Lector;
import com.elobservador.noticiero.entidades.Periodista;
import com.elobservador.noticiero.entidades.Usuario;
import com.elobservador.noticiero.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null) {

            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRole().toString());
            permisos.add(p);
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);

            if (usuario.getRole().toString().equals("PERIODISTA")) {
                Periodista periodista = (Periodista) usuarioRepository.findByEmail(usuario.getEmail());
                session.setAttribute("usuarioSession", periodista);
                return new User(periodista.getEmail(), periodista.getPassword(), permisos);

            } else if (usuario.getRole().toString().equals("LECTOR")) {

                Lector lector = (Lector) usuarioRepository.findByEmail(usuario.getEmail());
                session.setAttribute("usuarioSession", lector);
                return new User(lector.getEmail(), lector.getPassword(), permisos);

            } else if (usuario.getRole().toString().equals("ADMIN")) {

                session.setAttribute("usuariosession", usuario);
                return new User(usuario.getEmail(), usuario.getPassword(), permisos);
            }
        }
        return null;
    }
}
