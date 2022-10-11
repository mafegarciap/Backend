package com.example.clase35.repository;


import com.example.clase35.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
   /* List<T> listarElementos();
   T buscarPorId(int id);
    T buscarxCriterio(String criterio);
    void eliminar (int id);
    T guardar (T t);
    T modificar(T t);*/

}
