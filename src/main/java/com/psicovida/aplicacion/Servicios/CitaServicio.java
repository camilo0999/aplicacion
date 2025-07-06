package com.psicovida.aplicacion.Servicios;

import com.psicovida.aplicacion.Modelos.CitaModelo;

public interface CitaServicio {

  public Iterable<CitaModelo> obtenerCitas();

  public CitaModelo crearCita(CitaModelo cita);

  public CitaModelo actualizarCita(CitaModelo cita);

  public void eliminarCita(Long id);

}
