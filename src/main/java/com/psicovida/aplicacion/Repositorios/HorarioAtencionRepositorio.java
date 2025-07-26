package com.psicovida.aplicacion.Repositorios;

import com.psicovida.aplicacion.Modelos.HorarioAtencionModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioAtencionRepositorio extends JpaRepository<HorarioAtencionModelo, Long> {
  List<HorarioAtencionModelo> findByDoctorIdDoctor(Long idDoctor);

  List<HorarioAtencionModelo> findByDoctorIdDoctorAndDiaSemana(Long idDoctor, String diaSemana);
}