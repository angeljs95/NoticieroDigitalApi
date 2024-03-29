
package com.elobservador.noticiero.repositorio;

import com.elobservador.noticiero.entidades.Noticia;
import com.elobservador.noticiero.entidades.Periodista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NoticiaRepository extends JpaRepository < Noticia, Long> {

    List<Noticia> findByPeriodistaId(String id);

    Noticia findByIdAndPeriodistaId(Long Id, String idPeriodista);
   
    
    
}

 
    // buscaremos la noticia por su contenido
   // @Query(" SELECT n FROM Noticias n WHERE n.cuerpo= :cuerpo ")
   // public Noticia buscarPorCuerpo (@Param ("cuerpo") String cuerpo);
    
    
    //@Query("SELECT n FROM Noticias n WHERE n.titulo= :titulo ")
   // public Noticia buscarPorTitulo(@Param("titulo") String titulo);
    