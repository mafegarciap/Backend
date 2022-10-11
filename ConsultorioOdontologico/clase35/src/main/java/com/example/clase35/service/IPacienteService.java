package com.example.clase35.service;

import com.example.clase35.entities.Paciente;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {
    List<Paciente> listarPacientes();
    Optional<Paciente> buscarXid(Long id);
    Paciente guardarPaciente(Paciente paciente);
    Paciente actualizarPaciente(Paciente paciente);
    void eliminarPaciente(Long id);
}
