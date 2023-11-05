package com.elobservador.noticiero.controller;

import com.elobservador.noticiero.entidades.Noticia;
import com.elobservador.noticiero.excepcions.MiExceptions;
import com.elobservador.noticiero.service.NoticiaService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/noticia")
public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService;

    @GetMapping("/registrar")
    public String registrar() {

        return "noticias_crear.html";
    }

    @PostMapping("/registro")
    public String registro( @RequestParam(required=false) Long id ,@RequestParam String titulo,
            @RequestParam String cuerpo, ModelMap modelo) throws MiExceptions {

        try {
            noticiaService.crearNoticia(id,titulo, cuerpo);
            modelo.put("exito", "La noticia se ha cargado Correctamente");

            return "index.html";

        } catch (MiExceptions ex) {

            modelo.put("error", ex.getMessage());
            Logger.getLogger(NoticiaController.class.getName()).log(Level.SEVERE, null, ex);
            return "noticias_crear.html";
        }
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {
        List<Noticia> noticias = noticiaService.listarNoticias();
        modelo.put("noticias", noticias);

        return "noticias_lista.html";

    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, @ModelAttribute ("tuki") String tuk, ModelMap modelo) throws MiExceptions {

        if (tuk != null) {
        modelo.put("tuki", tuk);
        }
        modelo.put("noticia", noticiaService.getOne(id));
        return "noticias_modificar.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, String titulo, String cuerpo, RedirectAttributes redi , ModelMap modelo) throws MiExceptions {

        try {
            noticiaService.modificarNoticia(titulo, cuerpo, id);
            redi.addFlashAttribute("exito", "La noticia se modifico correctamente");
            return "redirect:../lista";
        } catch (MiExceptions ex) {
            redi.addFlashAttribute("error", ex.getMessage() );
            return "noticias_modificar.html";
        }
    }
}