package com.example.clase35.service;

import com.example.clase35.entities.Domicilio;
import com.example.clase35.entities.Odontologo;
import com.example.clase35.entities.Paciente;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OdontologoServiceImplTest {
    @Autowired
    private IOdontologoService odontologoService;

    //Crear odontologo y listar
    @Test
    @Order(1)
    public void testCrearOdontologo(){
        Odontologo odontologo= new Odontologo();
        odontologo.setApellido("Alvarez");
        odontologo.setNombre("Julian");
        odontologo.setMatricula("HBJ456");
        odontologoService.guardarOdontologo(odontologo);
        Odontologo odontologo1= new Odontologo();
        odontologo1.setApellido("Perez");
        odontologo1.setNombre("Juan");
        odontologo1.setMatricula("HBJ457");
        odontologoService.guardarOdontologo(odontologo1);
        Odontologo odontologo2= new Odontologo();
        odontologo2.setApellido("Lopez");
        odontologo2.setNombre("Julian");
        odontologo2.setMatricula("HBJ458");
        odontologoService.guardarOdontologo(odontologo2);

        List<Odontologo> odontologosGuardados = odontologoService.listarOdontologos();

        assertTrue(odontologosGuardados.size()==3);
    }

    //Listar odontologos por id
    @Test
    @Order(2)
    public void listarOdontologosPorIdTest(){
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorIdOdontologo(1L);

        assertTrue(odontologoBuscado != null);
    }
    //Listar
    @Test
    @Order(3)
    public void listarOdontologosTest(){
        List<Odontologo> listarOdontologos=odontologoService.listarOdontologos();
        assertTrue(listarOdontologos.size()==3);
    }

    //Modificar odontologo

   @Test
    @Order(4)
    public void actualizarOdontologo(){

        Odontologo odontologo3= new Odontologo();
        odontologo3.setApellido("Mendes");
        odontologo3.setNombre("Juan");
        odontologo3.setMatricula("HBJ457");
        odontologo3.setId(1L);
        odontologoService.actualizarOdontologo(odontologo3);
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorIdOdontologo(1L);

        assertEquals("Mendes",odontologoBuscado.get().getApellido());

    }
    //Eliminar odontologo
    @Test
    @Order(5)
    public void eliminarOdontologoTest(){
        odontologoService.eliminarOdontologo(1L);
        List<Odontologo> odontologosGuardados = odontologoService.listarOdontologos();
        assertTrue(odontologosGuardados.size()==2);
    }


    }



