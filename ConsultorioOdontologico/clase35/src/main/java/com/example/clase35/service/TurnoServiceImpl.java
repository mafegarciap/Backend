package com.example.clase35.service;

import com.example.clase35.entities.Turno;
import com.example.clase35.repository.PacienteRepository;
import com.example.clase35.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TurnoServiceImpl implements ITurnoService{
    @Autowired
    private TurnoRepository turnoRepository;

    @Override
    public List<Turno> listarTurnos() {
        return turnoRepository.findAll();
    }

    @Override
    public Optional<Turno> buscarPorIdTurno(Long id) {
        return turnoRepository.findById(id);
    }

    @Override
    public Turno guardarTurno(Turno turno) {
        return turnoRepository.save(turno);
    }

    @Override
    public Turno actualizarTurno(Turno turno) {
        return turnoRepository.save(turno);
    }

    @Override
    public void eliminarTurno(Long id) {
        turnoRepository.deleteById(id);
    }
}
