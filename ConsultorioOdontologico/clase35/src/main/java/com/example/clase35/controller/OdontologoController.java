package com.example.clase35.controller;

import com.example.clase35.entities.Odontologo;
import com.example.clase35.exceptions.BadRequestException;
import com.example.clase35.exceptions.ResourceNotFoundException;
import com.example.clase35.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController //usamos controller con vistas
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    private IOdontologoService odontologoService;
    //no lo instanciamos aca, porque lo hace spring con el autowired.

    @GetMapping
    public ResponseEntity<List<Odontologo>> traerOodontologos() {
        return ResponseEntity.ok(odontologoService.listarOdontologos());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> traerOodontologoID(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<Odontologo> pacienteBuscado = odontologoService.buscarPorIdOdontologo(id);
        if (pacienteBuscado != null) {
            return ResponseEntity.ok(pacienteBuscado.get());
        } else {
            throw new ResourceNotFoundException("No se encontro al odontólogo con id= " + id + " para realizar su eliminación. ¿Ingresó un id correcto?");
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Odontologo> registrarNuevoOdontologo(@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException {
        Optional<Odontologo> odontologoParaActualizar=odontologoService.buscarPorIdOdontologo(odontologo.getId());
        if (odontologoParaActualizar.isPresent()){
            return ResponseEntity.ok(odontologoService.actualizarOdontologo(odontologo));
        }
        else {
            throw new BadRequestException("No se pudo actualizar el odontólogo");
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Odontologo> odontologoParaBorrar=odontologoService.buscarPorIdOdontologo(id);
        if (odontologoParaBorrar.isPresent()){
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok(("Se elimino el odontólogo"));
        }
        else {

            throw new ResourceNotFoundException("No se encontro al odontólogoe con id= " + id + " para realizar su eliminación. ¿Ingresó un id correcto?");
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro al odontólogo con: "+ id +" para realizar su eliminación. ¿Ingresó un id correcto?");
        }
    }
}
