package com.psicovida.aplicacion.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psicovida.aplicacion.Modelos.PacienteModelo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface PacienteRepositorio extends JpaRepository<PacienteModelo, Long> {

  public PacienteModelo findByNumeroDocumento(String cedula);

  Page<PacienteModelo> findAll(Pageable pageable);

}
