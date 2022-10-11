package com.example.clase35.service;

import com.example.clase35.entities.Usuario;
import com.example.clase35.entities.UsuarioRol;
import com.example.clase35.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@Component
public class DataLoader implements ApplicationRunner {

    private UsuarioRepository usuarioRepository;
    @Autowired
    public DataLoader(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        String hash= passwordEncoder.encode("mafelalinda");
        String hash1= passwordEncoder.encode("mafelalinda1");
        Usuario usuario= new Usuario("Maria","mafe","mafe@gmail.com",hash , UsuarioRol.ROLE_USER);
        Usuario usuario1= new Usuario("Luis","luis","luis@gmail.com",hash1 , UsuarioRol.ROLE_ADMIN);
        usuarioRepository.save(usuario);
        usuarioRepository.save(usuario1);
    }
}
