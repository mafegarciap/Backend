package com.example.clase35.controller;

import com.example.clase35.entities.Odontologo;
import com.example.clase35.entities.Paciente;
import com.example.clase35.entities.Turno;
import com.example.clase35.exceptions.BadRequestException;
import com.example.clase35.exceptions.GlobalExceptions;
import com.example.clase35.exceptions.ResourceNotFoundException;
import com.example.clase35.service.IOdontologoService;
import com.example.clase35.service.IPacienteService;
import com.example.clase35.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.*;

import java.util.List;
import java.util.Optional;


@RestController //
@RequestMapping("/turnos")
public class TurnoController {

    //private static final Logger logger= Logger.getLogger(TurnoController.class);
    private static final Logger logger= Logger.getLogger(TurnoController.class);

    @Autowired
    private ITurnoService turnoService;
    @Autowired
    private IPacienteService pacienteService;
    @Autowired
    private IOdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno) throws BadRequestException {
        ResponseEntity<Turno> respuesta;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarXid(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorIdOdontologo(turno.getOdontologo().getId());
        if (pacienteBuscado != null && odontologoBuscado != null) {
            respuesta = ResponseEntity.ok(turnoService.guardarTurno(turno));
        } else {
            //logger.info("Se produce una excepción");
            throw new BadRequestException("No se pudo registrar el turno");
        }
        return respuesta;
    }

    @GetMapping
    public ResponseEntity<List<Turno>> traerTurnos() {
        return ResponseEntity.ok(turnoService.listarTurnos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTurnos(@PathVariable("id") Long id) throws ResourceNotFoundException {
        ResponseEntity response = null;
        if (turnoService.buscarPorIdTurno(id) != null) {
            turnoService.eliminarTurno(id);
            //response=new ResponseEntity<>(HttpStatus.NO_CONTENT);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            //response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            throw new ResourceNotFoundException("No se encontro el turno con id= " + id + " para realizar su eliminación. ¿Ingresaste el Id correcto?");
        }
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turno> actualizarTurno(@RequestBody Turno turno) throws BadRequestException {
        Optional<Turno> turnoParaActualizar=turnoService.buscarPorIdTurno(turno.getId());
        if(turnoParaActualizar.isPresent()){
            //logger.info("Se ejecutó la funcion Actualizar Turno.");
            return ResponseEntity.ok(turnoService.actualizarTurno(turno));
        }
        else{
            throw new BadRequestException("No se pudo actualizar la información, compruebe la información enviada.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable("id") Long id) throws  ResourceNotFoundException{
        Optional<Turno> turnoBuscado = turnoService.buscarPorIdTurno(id);
        if (turnoBuscado != null) {
            return ResponseEntity.ok(turnoBuscado.get());
        } else {
            throw new ResourceNotFoundException("No se encontro el turno con id= " + id + " para mostrarlo");
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
