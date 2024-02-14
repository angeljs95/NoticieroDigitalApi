
package com.elobservador.noticiero.controller;

import com.elobservador.noticiero.dtos.LectorDto;
import com.elobservador.noticiero.entidades.Lector;
import com.elobservador.noticiero.excepcions.MiExceptions;
import com.elobservador.noticiero.excepcions.RuntimeMiExceptions;
import com.elobservador.noticiero.service.LectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("/lector")
public class LectorController {

    @Autowired
    private LectorService lectorService;

    @PostMapping("/register")
    public ResponseEntity<Lector> registerLector(@RequestPart MultipartFile archivo, @RequestPart LectorDto ingreso) throws MiExceptions {

        try {
            ingreso.setArchivo(archivo);
            Lector newlector= lectorService.registrar(ingreso);
            return ResponseEntity.ok(newlector);
        } catch (MiExceptions ex) {;
            throw new RuntimeMiExceptions(ex.getMessage());
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Lector> updateLector(@RequestBody LectorDto lector, @PathVariable String id, ModelMap model) throws MiExceptions {

        try{
         if (lector != null) {
            lector.setId(id);
        }
        return ResponseEntity.ok(lectorService.updateLector(lector));
    } catch(Exception ex){
            throw new RuntimeMiExceptions(ex.getMessage());
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Lector> getLector(@PathVariable String id, ModelMap model){

        Lector lector= lectorService.getLector(id);
        if(lector== null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lector);
        //throw new RuntimeMiExceptions("La entidad con el ID " + id + " no se encontró.");
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<Lector>> listLector (ModelMap model){

        List<Lector> lectors= lectorService.getAll();
        if (lectors.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lectors);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteLector(@PathVariable String id, ModelMap model) throws MiExceptions{

        try{
            lectorService.deleteLector(id);
            return ResponseEntity.ok(true);
        }catch (RuntimeMiExceptions ex){
            throw new RuntimeMiExceptions( "no se ha encontrado el usuario");
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

