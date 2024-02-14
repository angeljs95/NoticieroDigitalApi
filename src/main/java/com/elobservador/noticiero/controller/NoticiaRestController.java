package com.elobservador.noticiero.controller;

import com.elobservador.noticiero.dtos.ComentarioDto;
import com.elobservador.noticiero.dtos.NoticiaDto;
import com.elobservador.noticiero.entidades.Comentario;
import com.elobservador.noticiero.entidades.Likes;
import com.elobservador.noticiero.entidades.Noticia;
import com.elobservador.noticiero.excepcions.MiExceptions;
import com.elobservador.noticiero.excepcions.RuntimeMiExceptions;
import com.elobservador.noticiero.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import java.util.List;

@RestController
@RequestMapping("/noticia")
@CrossOrigin
public class NoticiaRestController {

    @Autowired
    private NoticiaService noticiaService;

    @Autowired
    private CometarioService cometarioService;

    @Autowired
    private LectorService lectorService;

    @Autowired
    private PeriodistaService periodistaService;
    @Autowired
    private LikesService likesService;

  @PostMapping(value = "/created")
  public ResponseEntity<Noticia> saveNew(@RequestPart NoticiaDto noticiadto, @RequestPart MultipartFile archivo) throws MiExceptions {
        try {
            noticiadto.setArchivo(archivo);
            Noticia noticiaNew = noticiaService.saveNoticia(noticiadto);
            //Periodista periodista= periodistaService.getPeriodista(noticiadto.getPeriodistaId());
            periodistaService.addNotice(noticiaNew);
            return ResponseEntity.ok(noticiaNew);

        } catch (MiExceptions ex) {

            throw new RuntimeException(ex.getMessage());
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Noticia> obtenerNoticia(@PathVariable("id") long id, ModelMap model) {

        Noticia noticia = noticiaService.getNew(id);
        if (noticia==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(noticia);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Noticia> updateNoticia(@PathVariable long id, @RequestBody NoticiaDto noticia, ModelMap model) throws Exception {
        try {
            if (noticia != null) {
                noticia.setId(id);
            }
            return ResponseEntity.ok(noticiaService.updateNoticia(noticia));
        } catch (MiExceptions ex) {
            throw new RuntimeMiExceptions(ex.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteNoticia(@PathVariable long id, ModelMap model) {

        try {
            noticiaService.deleteNoticia(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            throw new RuntimeMiExceptions(e.getMessage());
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<Noticia>> listAllNotice() {

        List<Noticia> noticias = noticiaService.getAllNews();

        if (noticias.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(noticias);
    }

    @GetMapping("/listByPeriodistaId/{id}")
    public ResponseEntity<List<Noticia>> getByPeriodistaId(@PathVariable("id") String idP){
        List<Noticia> newsByPeriodistaId= noticiaService.newsByPeriodista(idP);
        if( newsByPeriodistaId.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(newsByPeriodistaId);
    }
//---------------------------COMENTARIOS----------------------------------------------------------------

    @PostMapping("/newComent/{id}")
    public ResponseEntity<Noticia> comentNews(@PathVariable("id") String lectorId, @RequestBody ComentarioDto comentario) {

        comentario.setLectorId(lectorId);
        Comentario newComentario = cometarioService.crearComentario(comentario);
        Noticia noticia = noticiaService.comentarNoticia(newComentario);
        lectorService.addComentario(newComentario);
        return ResponseEntity.ok(noticia);
    }

    @PostMapping("/replyComent/{id}")
    public ResponseEntity<Noticia> replyComentario(@PathVariable("id") String idPeriodista, @RequestBody ComentarioDto comentarioDto) {

        comentarioDto.setPeriodistaId(idPeriodista);
        Comentario newComentario = cometarioService.crearComentario(comentarioDto);
        Noticia noticia = noticiaService.comentarNoticia(newComentario);
        periodistaService.replyComentario(newComentario);
        return ResponseEntity.ok(noticia);

    }

    @GetMapping("/getCommentsByNotice/{noticiaId}")
    public ResponseEntity<List<Comentario>> commmentByNotice(@PathVariable("noticiaId") Long id) {

        List<Comentario> comments = cometarioService.getCommentsByNotice(id);

        return ResponseEntity.ok(comments);
    }
    //----------------------lIKES--------------------------------------------------------------

    @PostMapping("/addLike/{idNoticia}")
    public ResponseEntity<Noticia> addLike(@PathVariable("idNoticia") Long idNoticia, @RequestBody Likes like) {

        if (like.getLector() != null) {
            System.out.println("entro");
            Noticia noticia = noticiaService.getNew(idNoticia);
            like.setNoticia(noticia);
            likesService.addLike(like);
            noticiaService.darlike(noticia);
            System.out.println("salio");

            return ResponseEntity.ok(noticia);
        }
        System.out.println("fallo");
        return null;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException ex ) {
        return ResponseEntity.badRequest().body("Tipo de argumento incorrecto: " + ex.getName());
    }

    @ExceptionHandler(RuntimeMiExceptions.class)
    public ResponseEntity<String> idIncorrecto(RuntimeMiExceptions ex){
      return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(5000000);  // Tamaño máximo permitido del archivo
        return resolver;
    }

    //consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE

   /* @GetMapping("/query")
    public ResponseEntity<?> filtroQuery(@RequestParam("textoQuery") String textoQuery,
                                         ModelMap model){
        List<Noticia> noticias= noticiaService.listNoticias();
        if(noticias.contains(textoQuery)){
            return ResponseEntity.ok(noticias);
        }
return null;
    }*/
}
