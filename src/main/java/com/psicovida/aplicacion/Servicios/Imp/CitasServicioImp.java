package com.psicovida.aplicacion.Servicios.Imp;

import com.psicovida.aplicacion.Modelos.CitaModelo;
import com.psicovida.aplicacion.Repositorios.CitaRepositorio;
import com.psicovida.aplicacion.Servicios.CitaServicio;

public class CitasServicioImp implements CitaServicio {

  private final CitaRepositorio citaRepositorio;

  public CitasServicioImp(CitaRepositorio citaRepositorio) {
    this.citaRepositorio = citaRepositorio;
  }

  @Override
  public Iterable<CitaModelo> obtenerCitas() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'obtenerCitas'");
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
