package com.example.clase35.repository;

import com.example.clase35.entities.Usuario;
import com.example.clase35.entities.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
//@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUserName(String userName);
}
