package com.psicovida.aplicacion.Servicios;

import com.psicovida.aplicacion.Modelos.DoctorModelo;

public interface DoctorServicio {

  public DoctorModelo crearDoctor(DoctorModelo doctor);

  public DoctorModelo actualizarDoctor(DoctorModelo doctor);

  public void eliminarDoctor(Long id);

  public DoctorModelo obtenerDoctor(Long id);

  public Iterable<DoctorModelo> obtenerDoctores();

}
