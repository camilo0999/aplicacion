package com.psicovida.aplicacion.Servicios.Imp;

import org.springframework.stereotype.Service;

import com.psicovida.aplicacion.Modelos.CitaModelo;
import com.psicovida.aplicacion.Repositorios.CitaRepositorio;
import com.psicovida.aplicacion.Servicios.CitaServicio;
import com.psicovida.aplicacion.Servicios.HorarioAtencionServicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CitasServicioImp implements CitaServicio {

  private final CitaRepositorio citaRepositorio;
  private final HorarioAtencionServicio horarioAtencionServicio;

  @Autowired
  public CitasServicioImp(CitaRepositorio citaRepositorio, HorarioAtencionServicio horarioAtencionServicio) {
    this.citaRepositorio = citaRepositorio;
    this.horarioAtencionServicio = horarioAtencionServicio;
  }

  @Override
  public List<CitaModelo> obtenerCitas() {
    return citaRepositorio.findAll();
  }

  @Override
  public CitaModelo crearCita(CitaModelo cita) {
    // Validar disponibilidad del horario
    boolean disponible = horarioAtencionServicio
        .obtenerHorariosDisponibles(cita.getDoctor().getIdDoctor(), cita.getFecha().toString())
        .contains(cita.getHoraInicio().toString());
    if (!disponible) {
      throw new IllegalArgumentException("El horario seleccionado no está disponible para este doctor");
    }
    return citaRepositorio.save(cita);
  }

  @Override
  public void eliminarCita(Long id) {
    citaRepositorio.deleteById(id);
  }

  @Override
  public CitaModelo obtenerCitaPorId(Long id) {
    return citaRepositorio.findById(id).orElse(null);
  }

  @Override
  public CitaModelo confirmarCita(Long id) {
    CitaModelo cita = obtenerCitaPorId(id);
    if (cita == null)
      return null;
    cita.setEstado("CONFIRMADO");
    return citaRepositorio.save(cita);
  }

  @Override
  public CitaModelo cancelarCita(Long id) {
    CitaModelo cita = obtenerCitaPorId(id);
    if (cita == null)
      return null;
    cita.setEstado("CANCELADA");
    // motivoCancelacion ya no se guarda
    return citaRepositorio.save(cita);
  }
}
