package com.elobservador.noticiero.controller;

import com.elobservador.noticiero.entidades.Noticia;
import com.elobservador.noticiero.entidades.NoticiaDto;
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


//@Controller
//@RequestMapping("/noticia")
@Controller
@RequestMapping("/")
public class NoticiaController {
//----------------------Usando Controller---------------------------
    @Autowired
    private NoticiaService noticiaService;

    @GetMapping("/registrar")
    public String registrar(ModelMap model) {
        model.addAttribute("noticiaDto", new NoticiaDto());


        return "crear.html";
    }

    @PostMapping("/registro")
    public String registro( @RequestParam String titulo,
            @RequestParam String cuerpo, @RequestParam String copete, ModelMap modelo) throws MiExceptions {

        try {
            noticiaService.crearNoticia(titulo, cuerpo, copete);
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
    public String modificar(@PathVariable Long id, String titulo, String cuerpo,String copete, RedirectAttributes redi , ModelMap modelo) throws MiExceptions {

        try {
            noticiaService.modificarNoticia(titulo, cuerpo, copete, id);
            redi.addFlashAttribute("exito", "La noticia se modifico correctamente");
            return "redirect:../lista";
        } catch (MiExceptions ex) {
            redi.addFlashAttribute("error", ex.getMessage() );
            return "noticias_modificar.html";
        }
    }
}