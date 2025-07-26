package com.psicovida.aplicacion.Repositorios;

import com.psicovida.aplicacion.Modelos.CitaModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CitaRepositorio extends JpaRepository<CitaModelo, Long> {
  List<CitaModelo> findByDoctorIdDoctorAndFecha(Long idDoctor, LocalDate fecha);
}
