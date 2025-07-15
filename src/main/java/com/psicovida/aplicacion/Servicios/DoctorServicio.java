package com.psicovida.aplicacion.Servicios;

import com.psicovida.aplicacion.Modelos.DoctorModelo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DoctorServicio {

  public DoctorModelo crearDoctor(DoctorModelo doctor);

  public DoctorModelo actualizarDoctor(DoctorModelo doctor);

  public void eliminarDoctor(Long id);

  public DoctorModelo obtenerDoctor(Long id);

  public Iterable<DoctorModelo> obtenerDoctores();

  public DoctorModelo obtenerDoctorPorNumeroDocumento(String numeroDocumento);

  public DoctorModelo actualizarDoctorPorNumeroDocumento(String numeroDocumento, DoctorModelo doctor);

  Page<DoctorModelo> obtenerDoctoresPaginados(Pageable pageable);

}
