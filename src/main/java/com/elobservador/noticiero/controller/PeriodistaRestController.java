package com.elobservador.noticiero.controller;
import com.elobservador.noticiero.entidades.Periodista;
import com.elobservador.noticiero.excepcions.RuntimeMiExceptions;
import com.elobservador.noticiero.excepcions.MiExceptions;
import com.elobservador.noticiero.service.PeriodistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/periodista")
public class PeriodistaRestController {

    @Autowired
    private PeriodistaService periodistaService;

    @InitBinder
    //Método privado para evitar espacios en blanco
    private void stringBinder(WebDataBinder binder) {
        StringTrimmerEditor blankTrimmer =
                new StringTrimmerEditor(true);
        binder.registerCustomEditor(
                String.class, blankTrimmer);
    }

    @PostMapping("/registrado")
    public Periodista crearPeriodista(@RequestBody Periodista periodista, ModelMap model) throws MiExceptions {
        try {
            // return String.valueOf(periodista);
            System.out.println("entro al try");
            Periodista periodista1= periodistaService.registrar(periodista);
            model.put("exito","El registro ha sido exitoso");
            return periodista1;
        } catch (MiExceptions ex) {
            System.out.println("no entro");
            model.put("error", ex.getMessage());
            throw new RuntimeMiExceptions(ex.getMessage());
        }

    }
    @GetMapping(path = "/{id}")
    public Periodista obtenerPeriodista(@PathVariable String id, ModelMap model){

        Periodista periodista= periodistaService.getPeriodista(id);
                if( periodista!=null) {
                    return periodista;
                }
        throw new RuntimeMiExceptions("La entidad con el ID " + id + " no se encontró.");
    }

    @GetMapping("/getAll")
    public List<Periodista> obtenerPeriodistas( ModelMap model){

            List<Periodista> periodistas = periodistaService.listAll();
        model.put("periodistaList",periodistas);


        return periodistas;

    }

    @PutMapping("/update/{id}")
    public Periodista updatePeriodista(@RequestBody Periodista periodista, @PathVariable String id,
                                    ModelMap model) throws MiExceptions{

        try {
            if (periodista != null) {
            periodista.setId(id);
            Periodista respuesta= periodistaService.updatePeriodista(periodista);
            model.put("exito","El usuario: "+ periodista.getName()+" se ha actualizado correctamente");
            return respuesta;}
        } catch (MiExceptions ex){
            model.put("error", ex.getMessage());
            throw new RuntimeMiExceptions(ex.getMessage());
        }
        return null;
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deletePeriodistaa(@PathVariable String id, ModelMap model) throws MiExceptions {
        try{
           periodistaService.deletePeriodista(id);
           model.put("exito","Se ha eliminado correctamente");
           String respuesta= "Se ha eliminado corretamente";
           return ResponseEntity.ok(true);
    } catch (MiExceptions ex){
           model.put("error", "no se ha encontrardo al periodista"); // ex.getMessage());
            throw new RuntimeMiExceptions(" No se ha encontrado al periodista");
        }
    }


    @ExceptionHandler(RuntimeMiExceptions.class)
    public ResponseEntity<String> ExcepcionesRest(RuntimeMiExceptions ex) {
        String mensajeError = ex.getMessage();
        return new ResponseEntity<>(mensajeError, HttpStatus.NOT_FOUND);
    }
}
