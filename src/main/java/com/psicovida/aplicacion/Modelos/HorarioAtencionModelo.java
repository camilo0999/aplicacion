package com.psicovida.aplicacion.Modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "horario_atencion")
@Getter
@Setter
@NoArgsConstructor
public class HorarioAtencionModelo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idHorario;

  @ManyToOne
  @JoinColumn(name = "id_doctor")
  private DoctorModelo doctor;

  private String diaSemana; // Ejemplo: "LUNES", "MARTES", etc.

  private LocalTime horaInicio;

  private LocalTime horaFin;
}