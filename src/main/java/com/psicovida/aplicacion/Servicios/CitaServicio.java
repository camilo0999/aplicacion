package com.psicovida.aplicacion.Servicios;

import java.util.List;

import com.psicovida.aplicacion.Modelos.CitaModelo;

public interface CitaServicio {

  public List<CitaModelo> obtenerCitas();

  public CitaModelo crearCita(CitaModelo cita);

  public void eliminarCita(Long id);

  public CitaModelo obtenerCitaPorId(Long id);

  public CitaModelo confirmarCita(Long id);

  public CitaModelo cancelarCita(Long id);

}
