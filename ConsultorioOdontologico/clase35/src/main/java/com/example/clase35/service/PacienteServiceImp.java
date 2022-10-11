package com.example.clase35.service;

import com.example.clase35.entities.Paciente;
import com.example.clase35.exceptions.GlobalExceptions;
import com.example.clase35.repository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PacienteServiceImp implements IPacienteService{

    private static final Logger logger= Logger.getLogger(PacienteServiceImp.class);

    @Autowired
    private PacienteRepository pacienteRepository;


    @Override
    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    @Override
    public Optional<Paciente> buscarXid(Long id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente actualizarPaciente(Paciente paciente) {
        logger.info("Paciente Actualizado");
        return pacienteRepository.save(paciente);

    }

    @Override
    public void eliminarPaciente(Long id) {
        logger.info("Se eliminara un paciente");
        pacienteRepository.deleteById(id);
    }
}
