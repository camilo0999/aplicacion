package com.psicovida.aplicacion.Servicios.Imp;

import org.springframework.stereotype.Service;

import com.psicovida.aplicacion.Modelos.PacienteModelo;
import com.psicovida.aplicacion.Repositorios.PacienteRepositorio;
import com.psicovida.aplicacion.Servicios.PacienteServicio;

@Service
public class PacienteServicioImp implements PacienteServicio {

  private final PacienteRepositorio pacienteRepositorio;

  public PacienteServicioImp(PacienteRepositorio pacienteRepositorio) {
    this.pacienteRepositorio = pacienteRepositorio;
  }

  @Override
  public PacienteModelo crearPaciente(PacienteModelo paciente) {
    return pacienteRepositorio.save(paciente);
  }

  @Override
  public PacienteModelo actualizarPaciente(PacienteModelo paciente) {

    try {

      PacienteModelo pacienteActualizado = pacienteRepositorio.findById(paciente.getIdPaciente()).orElse(null);
      pacienteActualizado.setPrimerNombre(paciente.getPrimerNombre());
      pacienteActualizado.setSegundoNombre(paciente.getSegundoNombre());
      pacienteActualizado.setPrimerApellido(paciente.getPrimerApellido());
      pacienteActualizado.setSegundoApellido(paciente.getSegundoApellido());
      pacienteActualizado.setFechaNacimiento(paciente.getFechaNacimiento());
      pacienteActualizado.setSexo(paciente.getSexo());
      pacienteActualizado.setDireccion(paciente.getDireccion());
      pacienteActualizado.setTelefono(paciente.getTelefono());
      pacienteActualizado.setEmail(paciente.getEmail());
      return pacienteRepositorio.save(pacienteActualizado);

    } catch (Exception e) {
      return null;
    }

  }

  @Override
  public void eliminarPaciente(Long id) {
    pacienteRepositorio.deleteById(id);
  }

  @Override
  public PacienteModelo obtenerPaciente(Long id) {
    return pacienteRepositorio.findById(id).orElse(null);
  }

  @Override
  public Iterable<PacienteModelo> obtenerPacientes() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'obtenerPacientes'");
  }

}
