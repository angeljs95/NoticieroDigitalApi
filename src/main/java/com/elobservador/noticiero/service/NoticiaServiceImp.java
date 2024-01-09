package com.elobservador.noticiero.service;

import com.elobservador.noticiero.entidades.Noticia;
import com.elobservador.noticiero.entidades.NoticiaDto;
import com.elobservador.noticiero.repositorio.NoticiaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class NoticiaServiceImp implements NoticiaServicee {

    @Autowired
    private NoticiaRepository noticiaRepository;



   /* @Override
    public Noticia createNoticia(Noticia noticia) {


        return noticiaRepository.save(noticia) ;
    }*/

    @Override
    public NoticiaDto createNoticia(NoticiaDto noticiaDto) {

        Noticia noticia= new Noticia();
        noticia.setTitulo(noticiaDto.getTitulo());
        noticia.setCopete(noticiaDto.getCopete());
        noticia.setCuerpo(noticiaDto.getCuerpo());
        noticia.setAlta(new Date());
        noticia.setEstadoNoticia(true);


        return noticiaRepository.save(noticia);
    }

    @Override
    public Noticia getNoticia(long id) {
        return null;
    }

    @Override
    public void updateNoticia(Noticia noticia) {

    }

    @Override
    public void deleteNoticia(long id) {

    }

    @Override
    public List<Noticia> listNoticias() {
        return null;
    }
}
