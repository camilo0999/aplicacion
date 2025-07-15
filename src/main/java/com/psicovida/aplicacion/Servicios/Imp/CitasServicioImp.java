package com.psicovida.aplicacion.Servicios.Imp;

import org.springframework.stereotype.Service;

import com.psicovida.aplicacion.Modelos.CitaModelo;
import com.psicovida.aplicacion.Repositorios.CitaRepositorio;
import com.psicovida.aplicacion.Servicios.CitaServicio;

@Service
public class CitasServicioImp implements CitaServicio {

  private final CitaRepositorio citaRepositorio;

  public CitasServicioImp(CitaRepositorio citaRepositorio) {
    this.citaRepositorio = citaRepositorio;
  }

  @Override
  public Iterable<CitaModelo> obtenerCitas() {
    return citaRepositorio.findAll();
  }

  @Override
  public CitaModelo crearCita(CitaModelo cita) {
    return citaRepositorio.save(cita);
  }

  @Override
  public CitaModelo actualizarCita(CitaModelo cita) {
    return citaRepositorio.save(cita);
  }

  @Override
  public void eliminarCita(Long id) {
    citaRepositorio.deleteById(id);
  }

}
