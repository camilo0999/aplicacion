package com.psicovida.aplicacion.Servicios;

import com.psicovida.aplicacion.Modelos.PacienteModelo;

public interface PacienteServicio {

  public PacienteModelo crearPaciente(PacienteModelo paciente);

  public PacienteModelo actualizarPaciente(PacienteModelo paciente);

  public void eliminarPaciente(Long id);

  public PacienteModelo obtenerPaciente(Long id);

  public Iterable<PacienteModelo> obtenerPacientes();

}
