package com.elobservador.noticiero.service;

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
public class PeriodistaService {

    @Autowired
    PeriodistaRepository periodistaRepository;

    @Transactional
    public Periodista registrar(Periodista periodista) throws  MiExceptions{
        validar(periodista);
        Periodista periodista1= new Periodista();
        periodista1.setName(periodista.getName());
        periodista1.setEmail(periodista.getEmail());
        periodista1.setPassword(periodista.getPassword());
        periodista1.setNickName(periodista.getNickName());
        periodista1.setDocument(periodista.getDocument());
        periodista1.setAge(periodista.getAge());
        periodista1.setAddress(periodista.getAddress());
       periodista1.setRole(Role.PERIODISTA);
        periodista1.setActive(true);
        periodista1.setImagen(periodista.getImagen());

        return periodistaRepository.save(periodista1);
    }

    public Periodista updatePeriodista(Periodista periodistaActualizado) throws MiExceptions {
        // validar(periodistaActualizado);

      Optional<Periodista> respuesta= periodistaRepository.findById(periodistaActualizado.getId());
      if (respuesta.isPresent()){
        Periodista periodista= respuesta.get();
          actualizarEntidad(periodista,periodistaActualizado);
          return periodistaRepository.save(periodista);
          //return  periodistaAct;

      }
//
//        periodista1.setName(periodista.getName());
//        periodista1.setEmail(periodista.getEmail());
//        periodista1.setPassword(periodista.getPassword());
//        periodista1.setNickName(periodista.getNickName());
//        periodista1.setDocument(periodista.getDocument());
//        periodista1.setAge(periodista.getAge());
//        periodista1.setAddress(periodista.getAddress());
//        if(periodista.getRole()!=null ){
//            periodista1.setRole(periodista.getRole());
//        }
//        if(periodista.isActive()!=true){
//            periodista1.setActive(periodista.isActive());
//        }
//
//        periodista1.setImagen(periodista.getImagen());
    return null;
    }

    private void actualizarEntidad(Periodista periodista, Periodista periodistaActualizado){

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


    private void validar(Periodista periodista)throws MiExceptions {
        if(periodista.getName()== null || periodista.getName().isEmpty()){
            throw new MiExceptions("El nombre no puede estar vacio o ser nulo");
        }
        if(periodista.getEmail()== null ||periodista.getEmail().isEmpty()){
            throw new MiExceptions("El email no puede ser vacio o nulo");
        }
//        if(periodista.getNickName().isEmpty() || periodista.getNickName()==null){
//            throw new MiExceptions("Tiene que asignar un nickName valido");
//        }
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

        if(periodista.getAge()<18 || periodista.getAge()==null){
            throw new MiExceptions("Debe ser mayor a 18años y no debe estar vacio");
        }
    }





}
