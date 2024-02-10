package com.elobservador.noticiero.service;

import com.elobservador.noticiero.dtos.PeriodistaDto;
import com.elobservador.noticiero.entidades.Comentario;
import com.elobservador.noticiero.entidades.Imagen;
import com.elobservador.noticiero.entidades.Noticia;
import com.elobservador.noticiero.entidades.Periodista;
import com.elobservador.noticiero.enumerations.Role;
import com.elobservador.noticiero.excepcions.MiExceptions;
import com.elobservador.noticiero.repositorio.PeriodistaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.PropertyDescriptor;
import java.util.*;

@Service
@Transactional
public class PeriodistaService {

    @Autowired
    PeriodistaRepository periodistaRepository;
    @Autowired
    ImagenService imagenService;

    public Periodista registrar(PeriodistaDto periodista) throws  MiExceptions{
        validar(periodista);

        Periodista newPeriodista= new Periodista();
        newPeriodista.setName(periodista.getName());
        newPeriodista.setEmail(periodista.getEmail());
        newPeriodista.setPassword(periodista.getPassword());
        newPeriodista.setNickName(periodista.getNickName());
        newPeriodista.setDocument(periodista.getDocument());
        newPeriodista.setAge(periodista.getAge());
        newPeriodista.setAddress(periodista.getAddress());
        newPeriodista.setRole(Role.PERIODISTA);
        newPeriodista.setActive(true);
        newPeriodista.setMatricula(periodista.getMatricula());
        Imagen imagen= imagenService.guardar(periodista.getArchivo());

        if (imagen!= null){
            newPeriodista.setImagen(imagen);
        }

        return periodistaRepository.save(newPeriodista);
    }

    // hay que buscar los atributos que se puedan modificar de un periodista y asi emprolijar mas el codigo
    public Periodista updatePeriodista(PeriodistaDto periodista) throws MiExceptions {
        validar(periodista);

        Optional<Periodista> respuesta= periodistaRepository.findById(periodista.getId());
        if (respuesta.isPresent()){
            Periodista updatePeriodista= respuesta.get();
            actualizarEntidad(updatePeriodista, periodista);
            return periodistaRepository.save(updatePeriodista);
        }
     return  null;
    }

    public List<Periodista> listAll(){

        return periodistaRepository.findAll();
    }

    public Periodista getPeriodista(String id){

        return periodistaRepository.findById(id).orElse(null);
    }

    public void bajaPeriodista(String id){
        Optional<Periodista> respuesta= periodistaRepository.findById(id);
        if (respuesta.isPresent()){
            Periodista periodista=respuesta.get();
            periodista.setActive(false);
            periodistaRepository.save(periodista);
        }
    }

    public void deletePeriodista(String id) throws MiExceptions{

        Periodista periodista = getPeriodista(id);
        if (periodista != null) {
            periodistaRepository.deleteById(id);
        }
        throw new MiExceptions("No se ha encontrado el usuario");
    }

    public void replyComentario(Comentario comentario) {

        Optional<Periodista> respuesta = Optional.ofNullable(periodistaRepository.findById(comentario.getPeriodista().getId()).orElse(null));
        if (respuesta.isPresent()) {
            Periodista periodista = respuesta.get();
            periodista.getComentarios().add(comentario);
            periodistaRepository.save(periodista);
        }
    }

    public void save(Periodista periodista) {
        periodistaRepository.save(periodista);

    }

    private void validar(PeriodistaDto periodista)throws MiExceptions {
        if(periodista.getName()== null || periodista.getName().isEmpty()){
            throw new MiExceptions("El nombre no puede estar vacio o ser nulo");
        }
        if(periodista.getEmail()== null ||periodista.getEmail().isEmpty()){
            throw new MiExceptions("El email no puede ser vacio o nulo");
        }
       if(periodista.getNickName()==null || periodista.getNickName().isEmpty()){
            throw new MiExceptions("Tiene que asignar un nickName valido");
        }
        if(periodista.getPassword().length()< 8 ){
            throw new MiExceptions("El password debe tener al menos 8 caracteres");
        }
        if(periodista.getMatricula()== null){
            throw new MiExceptions("Debe ingresar su numero de matricula");
        }
        if(periodista.getAddress()== null || periodista.getAddress().isEmpty()){
            throw new MiExceptions("Debe seleccionar una direccion");
        }

        if(periodista.getDocument()== null){
            throw new MiExceptions("El documento no puede ser nulo");
        }

        if(periodista.getAge()==null || periodista.getAge()<18 ){
            throw new MiExceptions("Debe ser mayor a 18años y no debe estar vacio");
        }
    }

    // metodo de actualizacion de la entidad periodista
    private void actualizarEntidad(Periodista periodista, PeriodistaDto periodistaActualizado){

        BeanUtils.copyProperties(periodistaActualizado,periodista, getNullPropertyNames(periodistaActualizado));

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
