package com.example.clase35.service;

import com.example.clase35.entities.Odontologo;
import com.example.clase35.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OdontologoServiceImpl implements IOdontologoService{

    @Autowired
    private OdontologoRepository odontologoRepository;
    @Override
    public List<Odontologo> listarOdontologos() {
        return odontologoRepository.findAll();
    }

    @Override
    public Optional<Odontologo> buscarPorIdOdontologo(Long id) {
        return odontologoRepository.findById(id);
    }

    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    @Override
    public Odontologo actualizarOdontologo(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    @Override
    public void eliminarOdontologo(Long id) {
        odontologoRepository.deleteById(id);
    }
}
