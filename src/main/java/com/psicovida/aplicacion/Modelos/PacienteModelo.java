package com.psicovida.aplicacion.Modelos;

import java.util.Date;
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
@Table(name = "Paciente")
@Getter
@Setter
@NoArgsConstructor
public class PacienteModelo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idPaciente;

  private String primerNombre;

  private String segundoNombre;

  private String primerApellido;

  private String segundoApellido;

  private String tipoDocumento;

  private String numeroDocumento;

  private Date fechaNacimiento;

  private String sexo;

  private String direccion;

  private String telefono;

  private String email;

  @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
  private List<CitaModelo> citas;

}
