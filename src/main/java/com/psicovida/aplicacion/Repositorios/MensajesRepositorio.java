package com.psicovida.aplicacion.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.psicovida.aplicacion.Modelos.Mensajes;

public interface MensajesRepositorio extends JpaRepository<Mensajes, Long> {

  @Query("SELECT m FROM Mensajes m WHERE m.visto = false")
  List<Mensajes> findByVistoFalse();
}
