package com.psicovida.aplicacion.Modelos;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "doctor")
@Getter
@Setter
@NoArgsConstructor
public class DoctorModelo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idDoctor;

  private String primerNombre;

  private String segundoNombre;

  private String primerApellido;

  private String segundoApellido;

  private String numeroDocumento;

  private String tipoDocumento;

  private String especialidad;

  private String email;

  private String telefono;

  private String direccion;

  private String descripcion;

  @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
  private List<CitaModelo> citas;

}
