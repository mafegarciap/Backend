package com.example.clase35.service;

import com.example.clase35.entities.Domicilio;
import com.example.clase35.entities.Odontologo;
import com.example.clase35.entities.Paciente;
import com.example.clase35.entities.Turno;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yaml.snakeyaml.events.Event;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TurnoServiceImplTest {
    @Autowired
    private IOdontologoService odontologoService;
    @Autowired
    private IPacienteService pacienteService;
    @Autowired
    private ITurnoService turnoService;


    @Test
    @Order(1)
    public void  crearTurnoTest(){
        Domicilio domicilio= new Domicilio();
        domicilio.setCalle("Juncal");
        domicilio.setNumero(2569);
        domicilio.setLocalidad("martinez");
        domicilio.setProvincia("caba");

        Odontologo odontologo= new Odontologo();
        odontologo.setApellido("Alvarez");
        odontologo.setNombre("Julian");
        odontologo.setMatricula("HBJ456");
        odontologoService.guardarOdontologo(odontologo);


        Paciente paciente = new Paciente();
        paciente.setApellido("Gomez");
        paciente.setNombre("Perla");
        paciente.setEmail("mafe@ghj");
        paciente.setDni(123456);
        paciente.setFechaIngreso(LocalDate.of(2022,05,15));
        paciente.setDomicilio(domicilio);
        pacienteService.guardarPaciente(paciente);

        Turno turno= new Turno();
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);
        turno.setFecha(LocalDate.of(2022,11,06));
        turnoService.guardarTurno(turno);

        List<Turno> listaDeTurnos= turnoService.listarTurnos();

        assertTrue(listaDeTurnos.size()==1);
    }

    @Test
    @Order(2)
    public void buscarTurnoPorId(){

        Optional<Turno> turnoBuscado=turnoService.buscarPorIdTurno(1L);
            assertNotNull(turnoBuscado);
    }

    @Test
    @Order(3)
    public void listarTurnosTest(){
        List<Turno> listarTurnos=turnoService.listarTurnos();
        assertTrue(listarTurnos.size() >0);
    }

    @Test
    @Order(4)
    public void actualizarTurnoTest(){

        Domicilio domicilio= new Domicilio();
        domicilio.setCalle("Juncal");
        domicilio.setNumero(2569);
        domicilio.setLocalidad("martinez");
        domicilio.setProvincia("caba");

        Odontologo odontologo= new Odontologo();
        odontologo.setApellido("Alvarez");
        odontologo.setNombre("Jaime");
        odontologo.setMatricula("HBJ456");
        odontologoService.guardarOdontologo(odontologo);

        Paciente paciente = new Paciente();
        paciente.setApellido("Gomez");
        paciente.setNombre("Perla");
        paciente.setEmail("mafe@ghj");
        paciente.setDni(123456);
        paciente.setFechaIngreso(LocalDate.of(2022,05,15));
        paciente.setDomicilio(domicilio);
        pacienteService.guardarPaciente(paciente);

        Turno turno= new Turno();
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);
        turno.setFecha(LocalDate.of(2022,11,06));
        turno.setId(1L);
        turnoService.actualizarTurno(turno);
        Optional<Turno> turnoBuscado = turnoService.buscarPorIdTurno(1L);

        assertEquals("Jaime", turnoBuscado.get().getOdontologo().getNombre());

    }

    @Test
    @Order(5)
    public void eliminarTurno(){
        turnoService.eliminarTurno(1L);
        List<Turno> turnosGuardados = turnoService.listarTurnos();
        assertTrue(turnosGuardados.size()==0);
    }

}