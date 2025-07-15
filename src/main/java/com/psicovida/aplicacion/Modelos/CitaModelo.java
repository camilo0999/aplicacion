package com.psicovida.aplicacion.Modelos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cita")
@Getter
@Setter
@NoArgsConstructor
public class CitaModelo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idCita;

  @ManyToOne
  @JoinColumn(name = "id_paciente")
  private PacienteModelo paciente;

  @ManyToOne
  @JoinColumn(name = "id_doctor")
  private DoctorModelo doctor;

  private LocalDate fecha;

  private LocalTime horaInicio;

  private LocalTime horaFin;

  private String motivo;

  private String observaciones;

  private String estado;

  private String tipoCita;

  private String ubicacion;

  private String creadaPor;

  private LocalDateTime fechaCreacion;

  private LocalDateTime fechaActualizacion;

  private String notasPrivadas;

  private String medioContacto;

  private Boolean confirmada;

  private String motivoCancelacion;

}
