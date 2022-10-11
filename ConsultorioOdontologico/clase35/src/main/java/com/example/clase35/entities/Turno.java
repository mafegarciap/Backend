package com.example.clase35.entities;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "turnos")
public class Turno {
    @Id
    @SequenceGenerator(name = "turno_sequence",sequenceName = "turno_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "turno_sequence")
    private Long id;
    //(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name="id_paciente", referencedColumnName = "id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name="id_odontologo", referencedColumnName = "id")
    private Odontologo odontologo;

    @Column

    private LocalDate fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
