package com.elobservador.noticiero.service;

import com.elobservador.noticiero.entidades.Imagen;
import com.elobservador.noticiero.excepcions.MiExceptions;
import com.elobservador.noticiero.repositorio.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
public class ImagenService {

    @Autowired
    ImagenRepository imagenRepository;

    @Transactional
    public Imagen guardar(MultipartFile archivo) throws MiExceptions {

        if (archivo != null) {
            try {
                Imagen imagen = new Imagen();
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());
                return imagenRepository.save(imagen);
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

        }
        return null;
    }

}
