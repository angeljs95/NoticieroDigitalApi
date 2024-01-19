package com.elobservador.noticiero.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class inicioController {
    @GetMapping("")

    public String hola(){
        return "Hola Ya todo funciona";

    }
}
