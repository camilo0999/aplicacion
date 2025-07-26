package com.psicovida.aplicacion.Servicios.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psicovida.aplicacion.Modelos.Mensajes;
import com.psicovida.aplicacion.Repositorios.MensajesRepositorio;
import com.psicovida.aplicacion.Servicios.MensajeServicio;

@Service
public class MensajeServicioImp implements MensajeServicio {

  @Autowired
  private MensajesRepositorio mensajesRepositorio;

  @Override
  public List<Mensajes> findByVistoFalse() {
    return mensajesRepositorio.findByVistoFalse();
  }

  @Override
  public List<Mensajes> obtenerTodosLosMensajes() {
    return mensajesRepositorio.findAll();
  }

  @Override
  public Mensajes crearMensaje(Mensajes mensaje) {
    return mensajesRepositorio.save(mensaje);
  }

  @Override
  public Mensajes marcarComoVisto(Long id) {
    Mensajes mensaje = mensajesRepositorio.findById(id)
        .orElseThrow(() -> new RuntimeException("Mensaje no encontrado con id: " + id));
    mensaje.setVisto(true);
    return mensajesRepositorio.save(mensaje);
  }

  @Override
  public Mensajes obtenerMensajePorId(Long id) {
    return mensajesRepositorio.findById(id)
        .orElseThrow(() -> new RuntimeException("Mensaje no encontrado con id: " + id));
  }

  @Override
  public void eliminarMensaje(Long id) {
    if (!mensajesRepositorio.existsById(id)) {
      throw new RuntimeException("Mensaje no encontrado con id: " + id);
    }
    mensajesRepositorio.deleteById(id);
  }

}
