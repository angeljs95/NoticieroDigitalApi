package com.elobservador.noticiero.controller;

import com.elobservador.noticiero.entidades.Noticia;
import com.elobservador.noticiero.entidades.NoticiaDto;
import com.elobservador.noticiero.excepcions.MiExceptions;
import com.elobservador.noticiero.service.NoticiaServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.util.List;

@RestController
@RequestMapping("/noticia")
public class NoticiaRestController {

    @Autowired
    private NoticiaServiceImp noticiaService;

    @GetMapping("/create")
    public String createNoticia(ModelMap model) {
        model.put("hola", "hola");

        return "crear";
    }
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(5000000);  // Tamaño máximo permitido del archivo
        return resolver;
    }

    //consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE

  @PostMapping(value = "/created", consumes = "multipart/form-data")
    public Noticia noticiaCreada(@RequestBody NoticiaDto noticiadto, ModelMap model) throws MiExceptions {
        try {

            Noticia noticia1 = noticiaService.createNoticiaa(noticiadto);

           // return String.valueOf(noticia1;
            return noticia1;
        } catch (MiExceptions ex) {
            model.put("error", ex.getMessage());
           // return ex.getMessage();
            throw new RuntimeException(ex.getMessage());
        }
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<?> obtenerNoticia(@PathVariable long id, ModelMap model) {
        Noticia noticia1 = noticiaService.getNoticia(id);

        model.put("exito", "La noticia " + noticia1.getTitulo() + " ha sido encontrada");
        return ResponseEntity.ok(noticia1);
    }

    @GetMapping("/modificar")
    public String modificar() {

        return "craer";
    }

    @PostMapping("/edit/{id}")
    public String modificarNoticia(@PathVariable long id, @RequestBody Noticia noticia, ModelMap model) throws Exception {
        try {
            if (noticia != null) {
                noticia.setId(id);

                //   return ResponseEntity.ok(noticiaService.updateNoticia(noticia));
                model.put("exito", "Se ha actualizado la noticia");
                return String.valueOf(noticiaService.updateNoticia(noticia));
            }
        } catch (Exception ex) {
            model.put("error", "No se ha podido actualizar la noticia: " + ex.getMessage());
            throw new RuntimeException(ex.getMessage());

        }
        return "redirect:/modificar";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteNoticia(@PathVariable long id, ModelMap model) {

        try {
            noticiaService.deleteNoticia(id);
            model.put("exito", "La noticia " + id + " ha sido eliminada");
            return "vista";
        } catch (Exception e) {
            model.put("error", e.getMessage());
            return null;
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<Noticia>> listAllNotice(ModelMap model) {

        List<Noticia> noticias = noticiaService.listNoticias();
        model.put("noticias", noticias);

        return ResponseEntity.ok(noticias);
    }

    @GetMapping("/query")
    public ResponseEntity<?> filtroQuery(@RequestParam("textoQuery") String textoQuery,
                                         ModelMap model){

        List<Noticia> noticias= noticiaService.listNoticias();
        if(noticias.contains(textoQuery)){

            return ResponseEntity.ok(noticias);

        }

return null;
    }


}
