package com.elobservador.noticiero.service;

import com.elobservador.noticiero.entidades.Imagen;
import com.elobservador.noticiero.entidades.Noticia;
import com.elobservador.noticiero.entidades.NoticiaDto;
import com.elobservador.noticiero.excepcions.MiExceptions;
import com.elobservador.noticiero.repositorio.NoticiaDaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NoticiaServiceImp implements NoticiaServicee {

    @Autowired
    NoticiaDaoRepository noticiaDaoRepository;

    @Autowired ImagenService imagenService;

    @Override
    public Noticia createNoticia(Noticia noticia) throws MiExceptions {
        validar(noticia);
        Noticia n= new Noticia();

        n.setTitulo(noticia.getTitulo());
        n.setCopete(noticia.getCopete());
        n.setCuerpo(noticia.getCuerpo());
        n.setEstadoNoticia(true);
        n.setAlta(new Date());
        n.setPeriodista(noticia.getPeriodista());
        n.setAlbumImagenes(noticia.getAlbumImagenes());
        n.setComentarios(noticia.getComentarios());


        return noticiaDaoRepository.save(noticia);
    }

    public Noticia createNoticiaa(NoticiaDto noticia) throws MiExceptions {
       // validar(noticia);
        Noticia n= new Noticia();

        n.setTitulo(noticia.getTitulo());
        n.setCopete(noticia.getCopete());
        n.setCuerpo(noticia.getCuerpo());
        n.setEstadoNoticia(true);
        n.setAlta(new Date());
        n.setPeriodista(noticia.getPeriodista());
        n.setAlbumImagenes(noticia.getAlbumImagenes());
        n.setComentarios(noticia.getComentarios());

        Imagen imagen = imagenService.guardar(noticia.getArchivo());
        n.setImagen(imagen);

        return noticiaDaoRepository.save(n);
    }


    @Override
    public Noticia getNoticia(long id) {

      return noticiaDaoRepository.findById(id).orElse(null);
    }

    @Override
    public Noticia updateNoticia(Noticia noticia) throws MiExceptions {
        validar(noticia);
         Noticia n= getNoticia(noticia.getId());

        n.setTitulo(noticia.getTitulo());
        n.setCopete(noticia.getCopete());
        n.setCuerpo(noticia.getCuerpo());
        n.setEstadoNoticia(true);
        n.setPeriodista(noticia.getPeriodista());
        n.setAlta(noticia.getAlta());
        n.setAlbumImagenes(noticia.getAlbumImagenes());
        n.setComentarios(noticia.getComentarios());


           return  noticiaDaoRepository.save(n);

    }

    @Override
    public void deleteNoticia(long id) {
             Optional<Noticia> respuesta= noticiaDaoRepository.findById(id);
             if (respuesta.isPresent()){
                 Noticia noticia= respuesta.get();
                 noticiaDaoRepository.delete(noticia);
            }
    }

    @Override
    public List<Noticia> listNoticias() {
        return noticiaDaoRepository.findAll();
    }

    private void validar(Noticia noticia) throws MiExceptions{
        if (noticia.getTitulo()== null && noticia.getTitulo().isEmpty()){
            throw new MiExceptions("El titulo no puede estar en blanco o ser nulo ");
        }

        if (noticia.getCopete()== null && noticia.getCopete().isEmpty()){
            throw new MiExceptions("Debe agregar una reseña para la noticia");
        }
        if (noticia.getCuerpo()== null && noticia.getCuerpo().isEmpty()){
            throw new MiExceptions("El cuerpo de la noticia no puede estar en blanco o ser nulo ");
        }
    }


  /*  @Override
    public NoticiaDto createNoticia(NoticiaDto noticiaDto) {

        Noticia noticia= new Noticia();
        noticia.setTitulo(noticiaDto.getTitulo());
        noticia.setCopete(noticiaDto.getCopete());
        noticia.setCuerpo(noticiaDto.getCuerpo());
        noticia.setPeriodista(noticiaDto.getPeriodista());
        noticia.setAlta(new Date());
        noticia.setEstadoNoticia(true);
        noticiaRepository.save(noticia);
        return noticiaDto;
    }*/

}

// direccion ip base de datos :    35.193.112.157