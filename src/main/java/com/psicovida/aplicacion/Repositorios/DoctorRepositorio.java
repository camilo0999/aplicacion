package com.psicovida.aplicacion.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psicovida.aplicacion.Modelos.DoctorModelo;

@Repository
public interface DoctorRepositorio extends JpaRepository<DoctorModelo, Long> {

}
