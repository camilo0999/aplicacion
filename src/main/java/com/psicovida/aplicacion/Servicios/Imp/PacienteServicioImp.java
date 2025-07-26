package com.psicovida.aplicacion.Servicios.Imp;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.psicovida.aplicacion.Modelos.PacienteModelo;
import com.psicovida.aplicacion.Repositorios.PacienteRepositorio;
import com.psicovida.aplicacion.Servicios.PacienteServicio;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PacienteServicioImp implements PacienteServicio {

  private final PacienteRepositorio pacienteRepositorio;

  @Autowired
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

      PacienteModelo pacienteActualizado = pacienteRepositorio.findByNumeroDocumento(paciente.getNumeroDocumento());
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
  public PacienteModelo obtenerPaciente(String cedula) {
    return pacienteRepositorio.findByNumeroDocumento(cedula);
  }

  @Override
  public List<PacienteModelo> obtenerPacientes() {
    return pacienteRepositorio.findAll();
  }

  @Override
  public Page<PacienteModelo> obtenerPacientesPaginados(Pageable pageable) {
    return pacienteRepositorio.findAll(pageable);
  }

  @Override
  public PacienteModelo buscarPorDocumento(String documento) {
    return pacienteRepositorio.findByNumeroDocumento(documento);
  }
}
