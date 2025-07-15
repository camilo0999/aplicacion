package com.psicovida.aplicacion.Servicios.Imp;

import org.springframework.stereotype.Service;

import com.psicovida.aplicacion.Modelos.DoctorModelo;
import com.psicovida.aplicacion.Repositorios.DoctorRepositorio;
import com.psicovida.aplicacion.Servicios.DoctorServicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class DoctorServicioImp implements DoctorServicio {

  private final DoctorRepositorio doctorRepositorio;

  public DoctorServicioImp(DoctorRepositorio doctorRepositorio) {
    this.doctorRepositorio = doctorRepositorio;
  }

  @Override
  public DoctorModelo crearDoctor(DoctorModelo doctor) {

    return doctorRepositorio.save(doctor);
  }

  @Override
  public DoctorModelo actualizarDoctor(DoctorModelo doctor) {
    if (doctor.getIdDoctor() == null || !doctorRepositorio.existsById(doctor.getIdDoctor())) {
      return null;
    }
    return doctorRepositorio.save(doctor);
  }

  @Override
  public void eliminarDoctor(Long id) {

    doctorRepositorio.deleteById(id);
  }

  @Override
  public DoctorModelo obtenerDoctor(Long id) {

    return doctorRepositorio.findById(id).orElse(null);
  }

  @Override
  public Iterable<DoctorModelo> obtenerDoctores() {
    return doctorRepositorio.findAll();
  }

  @Override
  public DoctorModelo obtenerDoctorPorNumeroDocumento(String numeroDocumento) {
    return doctorRepositorio.findByNumeroDocumento(numeroDocumento);
  }

  @Override
  public DoctorModelo actualizarDoctorPorNumeroDocumento(String numeroDocumento, DoctorModelo doctor) {
    DoctorModelo existente = doctorRepositorio.findByNumeroDocumento(numeroDocumento);
    if (existente == null) {
      return null;
    }
    doctor.setIdDoctor(existente.getIdDoctor());
    return doctorRepositorio.save(doctor);
  }

  @Override
  public Page<DoctorModelo> obtenerDoctoresPaginados(Pageable pageable) {
    return doctorRepositorio.findAll(pageable);
  }
}
