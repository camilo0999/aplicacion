package com.psicovida.aplicacion.Servicios;

import java.util.List;

import com.psicovida.aplicacion.Modelos.Mensajes;

public interface MensajeServicio {

  List<Mensajes> findByVistoFalse();

  List<Mensajes> obtenerTodosLosMensajes();

  Mensajes crearMensaje(Mensajes mensaje);

  Mensajes marcarComoVisto(Long id);

  Mensajes obtenerMensajePorId(Long id);

  void eliminarMensaje(Long id);

}