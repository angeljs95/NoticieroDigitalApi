package com.elobservador.noticiero.controller;

import com.elobservador.noticiero.entidades.Imagen;
import com.elobservador.noticiero.entidades.Noticia;
import com.elobservador.noticiero.entidades.Usuario;
import com.elobservador.noticiero.excepcions.MiExceptions;
import com.elobservador.noticiero.service.ImagenService;
import com.elobservador.noticiero.service.NoticiaService;
import com.elobservador.noticiero.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/images")
public class ImagenRestController {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    NoticiaService noticiaService;
    @Autowired
    ImagenService imagenService;


    @PostMapping("/update/{idUsuario}")
    public ResponseEntity<byte[]> updateImagen(@RequestPart MultipartFile archivo, @PathVariable("idUsuario") String id) throws MiExceptions {
        if (!archivo.isEmpty()) {
            Imagen imagen = imagenService.guardar(archivo);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imagen.getContenido(), headers, HttpStatus.OK);
        }
        return null;

    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<byte[]> imagenUsuario(@PathVariable String id) {
        Usuario usuario = usuarioService.getUser(id);
        byte[] imagen = usuario.getImagen().getContenido();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    }

    @GetMapping("/noticiaImagen/{id}")
    public ResponseEntity<byte[]> imageNoticia(@PathVariable Long id) {
        Noticia noticia = noticiaService.getNew(id);
        byte[] imagen = noticia.getImagen().getContenido();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    }

    @GetMapping("/noticia/{id}")
    @ResponseBody
    public List<byte[]> imagenes(@PathVariable Long id) {

        Noticia noticia = noticiaService.getNew(id);
        int i;
        List<byte[]> imagenes = new ArrayList<byte[]>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        for (i = 0; i < noticia.getAlbumImagenes().size(); i++) {
            Imagen img = noticia.getAlbumImagenes().get(i);
            imagenes.add(img.getContenido());
        }
        return imagenes;
    }
}
