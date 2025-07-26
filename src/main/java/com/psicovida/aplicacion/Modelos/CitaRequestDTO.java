package com.psicovida.aplicacion.Modelos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class CitaRequestDTO {
  private Long idDoctor;
  private String documentoPaciente;
  private String fecha; // formato YYYY-MM-DD
  private String horaInicio; // formato HH:mm:ss
  private String horaFin; // formato HH:mm:ss
  private String estado;
}