package com.elobservador.noticiero.controller;

import com.elobservador.noticiero.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminRestController {


    @Autowired
    UsuarioService usuarioService;

    @PreAuthorize("HasAnyRole('ROLE_ADMIN')")
    @GetMapping("/dashboard")
    public String adminservice() {

        return "hola";
    }


}
