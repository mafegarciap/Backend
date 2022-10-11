package com.example.clase35.controller;

import com.example.clase35.entities.Paciente;
import com.example.clase35.exceptions.BadRequestException;
import com.example.clase35.exceptions.ResourceNotFoundException;
import com.example.clase35.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController //
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private IPacienteService pacienteService;
    //no lo instanciamos aca, porque lo hace spring con el autowired.

    @GetMapping
    public ResponseEntity<List<Paciente>> traerPacientes() {
        return ResponseEntity.ok(pacienteService.listarPacientes()) ;

    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> traerPacienteID(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarXid(id);
        if (pacienteBuscado != null) {
            return ResponseEntity.ok(pacienteBuscado.get());
        } else {
            throw new ResourceNotFoundException("No se encontro al paciente con id= " + id + " para realizar su eliminación. Error al ingresar el id.");
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Paciente> registrarNuevoPaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizarPaciente(@RequestBody Paciente paciente) throws BadRequestException {
        Optional<Paciente> pacienteParaActualizar=pacienteService.buscarXid(paciente.getId());
        if (pacienteParaActualizar.isPresent()){
            return ResponseEntity.ok(pacienteService.actualizarPaciente(paciente));
        }
        else {
            throw new BadRequestException("No se pudo actualizar el paciente");
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteAborrar=pacienteService.buscarXid(id);
        if (pacienteAborrar.isPresent()){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok(("Se elimino el paciente"));
        }
        else {
            //estoy en el caso de no encontrar el id en nuestra base de datos
            throw new ResourceNotFoundException("No se encontro al paciente con id= " + id + " para realizar su eliminación. Error al ingresar el id.");
            /*
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro al paciente con: "+ id + " para realizar su eliminación. ¿Ingresó un id correcto?");
            */
        }
    }
}
