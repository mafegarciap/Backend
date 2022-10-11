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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PacienteServiceImpTest {

    @Autowired
    private IPacienteService pacienteService;


    @Test
    @Order(1)
    public void agregarPacienteTest(){
        Domicilio domicilio= new Domicilio();
        domicilio.setCalle("Juncal");
        domicilio.setNumero(2569);
        domicilio.setLocalidad("martinez");
        domicilio.setProvincia("caba");

        Paciente paciente = new Paciente();
        paciente.setApellido("Gomez");
        paciente.setNombre("Mafe");
        paciente.setEmail("mafe@ghj");
        paciente.setDni(123456);
        paciente.setFechaIngreso(LocalDate.of(2022,05,15));
        paciente.setDomicilio(domicilio);
        pacienteService.guardarPaciente(paciente);

        Optional<Paciente> pacienteBuscado = pacienteService.buscarXid(1L);
        assertNotNull(pacienteBuscado);
        assertEquals("Gomez",pacienteBuscado.get().getApellido());
    }
    @Test
    @Order(2)
    public void buscarPacientePorIDTest(){

        Optional<Paciente> pacienteBuscado=pacienteService.buscarXid(1L);
        assertNotNull(pacienteBuscado);
    }


    @Test
    @Order(3)
    public void listarPacientesTest(){
        List<Paciente> listarPacientes=pacienteService.listarPacientes();
        assertTrue(listarPacientes.size()==1);
    }

    @Test
    @Order(4)
    public void actualizarPacienteTest(){
        Domicilio domicilio= new Domicilio();
        domicilio.setCalle("Pueyrredon");
        domicilio.setNumero(2569);
        domicilio.setLocalidad("Recoleta");
        domicilio.setProvincia("CABA");

        Paciente paciente = new Paciente();
        paciente.setApellido("Garcia");
        paciente.setNombre("Mafe");
        paciente.setEmail("mafe@ghj");
        paciente.setDni(123456);
        paciente.setFechaIngreso(LocalDate.of(2022,05,15));
        paciente.setDomicilio(domicilio);
        paciente.setId(1L);
        pacienteService.actualizarPaciente(paciente);
        Optional<Paciente> pacienteBuscado = pacienteService.buscarXid(1L);

        assertEquals("Garcia", pacienteBuscado.get().getApellido());

    }

    @Test
    @Order(5)
    public void eliminarPaciente(){
        pacienteService.eliminarPaciente(1L);
        List<Paciente> pacientesGuardados = pacienteService.listarPacientes();
        assertTrue(pacientesGuardados.size()==0);
    }


}