package com.example.clase35.service;

import com.example.clase35.entities.Paciente;
import com.example.clase35.entities.Turno;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    List<Turno> listarTurnos();
    Optional<Turno> buscarPorIdTurno(Long id);
    Turno guardarTurno(Turno turno);
    Turno actualizarTurno(Turno turno);
    void eliminarTurno(Long id);
}
