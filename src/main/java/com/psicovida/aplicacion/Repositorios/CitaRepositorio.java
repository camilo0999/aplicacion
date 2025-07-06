package com.psicovida.aplicacion.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psicovida.aplicacion.Modelos.CitaModelo;

public interface CitaRepositorio extends JpaRepository<CitaModelo, Long> {

}
