package com.psicovida.aplicacion.Servicios;

import java.util.List;

import com.psicovida.aplicacion.Modelos.PacienteModelo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PacienteServicio {

  public PacienteModelo crearPaciente(PacienteModelo paciente);

  public PacienteModelo actualizarPaciente(PacienteModelo paciente);

  public void eliminarPaciente(Long id);

  public PacienteModelo obtenerPaciente(String cedula);

  public List<PacienteModelo> obtenerPacientes();

  Page<PacienteModelo> obtenerPacientesPaginados(Pageable pageable);

  PacienteModelo buscarPorDocumento(String documento);
}
