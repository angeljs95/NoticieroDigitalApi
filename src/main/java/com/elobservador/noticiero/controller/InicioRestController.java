package com.elobservador.noticiero.controller;

import com.elobservador.noticiero.entidades.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(" ")
public class InicioRestController {

    @PreAuthorize("hasAnyRole('ROLE_LECTOR', 'ROLE_ADMIN' , 'ROLE_PERIODISTA')")
    @GetMapping("/iniciando")
    public ResponseEntity<String> inicio(HttpSession session) {

        Usuario logueado = (Usuario) session.getAttribute("usuarioSession");
        if (logueado.getRole().toString().equals("ADMIN")) {

            return ResponseEntity.ok("Bienvenido Admin: " + logueado.getNickName());
        }
        return ResponseEntity.ok("Bienvenido " + logueado.getNickName());
    }


}
