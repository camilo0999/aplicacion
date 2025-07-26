package com.psicovida.aplicacion.Modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Mensajes {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String fecha;

  private String hora;

  private String nombreCompleto;

  private String asunto;

  private String email;

  private String telefono;

  private Boolean visto;

  @Column(columnDefinition = "VARCHAR(500)")
  private String mensaje;

}
