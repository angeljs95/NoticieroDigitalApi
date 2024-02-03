package com.elobservador.noticiero.controller;
import com.elobservador.noticiero.dtos.PeriodistaDto;
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


    @PostMapping("/register")
    public ResponseEntity<Periodista> registerPeriodista(@RequestBody PeriodistaDto periodista, ModelMap model) throws MiExceptions {
        try {
            Periodista newPeriodista= periodistaService.registrar(periodista);
            return ResponseEntity.ok(newPeriodista);
        } catch (MiExceptions ex) {
            throw new RuntimeMiExceptions(ex.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Periodista> updatePeriodista (@RequestBody PeriodistaDto periodista, @PathVariable String id,
                                                        ModelMap model) throws MiExceptions{

        try {
            if (periodista != null) {
                periodista.setId(id);
            }
            return ResponseEntity.ok(periodistaService.updatePeriodista(periodista));
        } catch (MiExceptions ex){
            throw new RuntimeMiExceptions(ex.getMessage());
        }
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<Periodista> getPeriodista(@PathVariable String id, ModelMap model){

        Periodista periodista= periodistaService.getPeriodista(id);
                if( periodista==null) {
                    return ResponseEntity.noContent().build();
                }
                    return ResponseEntity.ok(periodista);
        //throw new RuntimeMiExceptions("La entidad con el ID " + id + " no se encontró.");
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<Periodista>> listPeriodistas( ModelMap model){

            List<Periodista> listperiodistas = periodistaService.listAll();
    if(listperiodistas.isEmpty()){
        return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(listperiodistas);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deletePeriodistaa(@PathVariable String id, ModelMap model) throws MiExceptions {
        try{
           periodistaService.deletePeriodista(id);
           return ResponseEntity.ok(true);
    } catch (MiExceptions ex){
            throw new RuntimeMiExceptions(" No se ha encontrado al periodista");
        }
    }


    @ExceptionHandler(RuntimeMiExceptions.class)
    public ResponseEntity<String> ExcepcionesRest(RuntimeMiExceptions ex) {
        String mensajeError = ex.getMessage();
        return new ResponseEntity<>(mensajeError, HttpStatus.NOT_FOUND);
    }

    @InitBinder
    //Método privado para evitar espacios en blanco
    private void stringBinder(WebDataBinder binder) {
        StringTrimmerEditor blankTrimmer =
                new StringTrimmerEditor(true);
        binder.registerCustomEditor(
                String.class, blankTrimmer);
    }
}
