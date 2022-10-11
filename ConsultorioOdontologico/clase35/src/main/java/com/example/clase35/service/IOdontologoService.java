package com.example.clase35.service;

import com.example.clase35.entities.Odontologo;


import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    List<Odontologo> listarOdontologos();
    Optional<Odontologo> buscarPorIdOdontologo(Long id);
    Odontologo guardarOdontologo(Odontologo odontologo);
    Odontologo actualizarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(Long id);
}
