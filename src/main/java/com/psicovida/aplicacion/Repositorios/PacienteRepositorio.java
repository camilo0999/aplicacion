package com.psicovida.aplicacion.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psicovida.aplicacion.Modelos.PacienteModelo;

@Repository
public interface PacienteRepositorio extends JpaRepository<PacienteModelo, Long> {

}
