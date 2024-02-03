package com.elobservador.noticiero.service;

import com.elobservador.noticiero.dtos.LectorDto;
import com.elobservador.noticiero.entidades.Comentario;
import com.elobservador.noticiero.entidades.Imagen;
import com.elobservador.noticiero.entidades.Lector;
import com.elobservador.noticiero.entidades.Noticia;
import com.elobservador.noticiero.enumerations.Role;
import com.elobservador.noticiero.excepcions.MiExceptions;
import com.elobservador.noticiero.repositorio.LectorRepository;
import com.elobservador.noticiero.repositorio.NoticiaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class LectorService {

     @Autowired
    private LectorRepository lectorRepository;
     @Autowired
     ImagenService imagenService;

     @Autowired
     private NoticiaRepository noticiaRepository;

     @Autowired
     private CometarioService cometarioService;

     public Lector registrar(LectorDto ingreso) throws MiExceptions{

         validar(ingreso);

         Lector lector= new Lector();
         lector.setName(ingreso.getName());
         lector.setEmail(ingreso.getEmail());
         lector.setPassword(ingreso.getPassword());
         lector.setNickName(ingreso.getNickName());
         lector.setDocument(ingreso.getDocument());
         lector.setAge(ingreso.getAge());
         lector.setAddress(ingreso.getAddress());
         lector.setRole(Role.LECTOR);
         lector.setActive(true);
         Imagen imagen= imagenService.guardar(ingreso.getArchivo());
         if(imagen!= null){
             lector.setImagen(imagen);
         }

         return lectorRepository.save(lector);
     }

     public Lector updateLector(LectorDto lectorActualizado) {

         Optional<Lector> respuesta = lectorRepository.findById(lectorActualizado.getId());
         if (respuesta.isPresent()) {
             Lector lector = respuesta.get();
             actualizarEntidad(lector, lectorActualizado);
             return lectorRepository.save(lector);

         }
         return  null;
     }



     @Transactional
     public void deleteLector(String id){

         Optional<Lector> respuesta= lectorRepository.findById(id);

         if (respuesta.isPresent()){

             lectorRepository.deleteById(id);
         }
     }

     public void bajaLector(String id){

         Optional<Lector> respuesta= lectorRepository.findById(id);
         if (respuesta.isPresent()){
             Lector lector= respuesta.get();
             lector.setActive(false);
             lectorRepository.save(lector);
         }
     }

     public List<Lector> getAll(){
         return lectorRepository.findAll();
     }

     public Lector getLector(String id){
       return lectorRepository.findById(id).orElse(null);
     }

    public void addComentario(Comentario comentario){

         if(comentario!= null) {

             Optional<Lector> respuesta = lectorRepository.findById(comentario.getLector().getId());
             if (respuesta.isPresent()) {
                 Lector lector = respuesta.get();
                 Comentario comentario1= cometarioService.crearComentario(comentario);
                 lector.getComentariosLector().add(comentario1);
                 lectorRepository.save(lector);
             }
         }
     }

    @Transactional
    public void RemoveComentario( Long id ) throws MiExceptions {
        try {
            Comentario comentario = cometarioService.getOne(id);
            if (comentario != null) {

                Optional<Lector> respuesta = lectorRepository.findById(comentario.getLector().getId());
                if (respuesta.isPresent()) {
                    Lector lector = respuesta.get();
                    cometarioService.deleteComentario(comentario.getId());
                    lector.getComentariosLector().remove(comentario);
                    lectorRepository.save(lector);
                }
            }
        } catch (MiExceptions ex) {
            ex.getMessage();
        }
    }
     public void darlike(Noticia noticia, String idLector ){

         Lector lector = lectorRepository.findById(idLector).orElseThrow(() -> new RuntimeException("Lector no encontrado"));
         Noticia noticia1 = noticiaRepository.findById(noticia.getId()).orElseThrow(() -> new RuntimeException("Noticia no encontrada"));

         lector.getLikesNotices().add(noticia);
         noticia1.getLectorLikes().add(lector);
         noticia1.darLike();
         lectorRepository.save(lector);
         noticiaRepository.save(noticia1);

      //   Optional<Noticia> noti= noticiaRepository.findById(noticia.getId());
//         if(noticia!= null){
//             noticia.darLike();
//         }
     }

     public void darUnlike(Noticia noticia, String idLector){
         Lector lector = lectorRepository.findById(idLector).orElseThrow(() -> new RuntimeException("Lector no encontrado"));
         Noticia noticia1 = noticiaRepository.findById(noticia.getId()).orElseThrow(() -> new RuntimeException("Noticia no encontrada"));

         lector.getLikesNotices().remove(noticia);
         noticia1.getLectorLikes().remove(lector);
         noticia1.sacarLike();
         lectorRepository.save(lector);
         noticiaRepository.save(noticia1);


     }

    private void validar(LectorDto lector)throws MiExceptions {
        if(lector.getName()== null || lector.getName().isEmpty()){
            throw new MiExceptions("El nombre no puede estar vacio o ser nulo");
        }
        if(lector.getEmail()== null ||lector.getEmail().isEmpty()){
            throw new MiExceptions("El email no puede ser vacio o nulo");
        }
        if( lector.getNickName()==null||lector.getNickName().isEmpty()){
            throw new MiExceptions("Tiene que asignar un nickName valido");
        }
        if(lector.getPassword().length()< 8 ){
            throw new MiExceptions("El password debe tener al menos 8 caracteres");
        }

        if(lector.getAddress()== null || lector.getAddress().isEmpty()){
            throw new MiExceptions("Debe seleccionar una direccion");
        }

        if(lector.getDocument()== null){
            throw new MiExceptions("El documento no puede ser nulo");
        }

        if(lector.getAge()==null || lector.getAge()<18 ){
            throw new MiExceptions("Debe ser mayor a 18años y no debe estar vacio");
        }
    }

    // Metodo para actualizar la entidad lector
    private void actualizarEntidad(Lector lector, LectorDto lectorActualizado){

        BeanUtils.copyProperties(lectorActualizado,lector, getNullPropertyNames(lectorActualizado));

    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }




}
