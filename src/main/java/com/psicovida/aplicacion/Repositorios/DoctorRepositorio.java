package com.psicovida.aplicacion.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psicovida.aplicacion.Modelos.DoctorModelo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface DoctorRepositorio extends JpaRepository<DoctorModelo, Long> {

  DoctorModelo findByNumeroDocumento(String numeroDocumento);

  Page<DoctorModelo> findAll(Pageable pageable);
}
