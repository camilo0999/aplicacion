package com.psicovida.aplicacion.Controladores;

import com.psicovida.aplicacion.Modelos.Mensajes;
import com.psicovida.aplicacion.Servicios.MensajeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/mensaje")
public class MensajeControlador {

  @Autowired
  private MensajeServicio mensajeServicio;

  /**
   * Crea un nuevo mensaje.
   * POST /api/v1/mensaje
   * 
   * @param mensaje El objeto mensaje a crear.
   * @return ResponseEntity con el mensaje creado y el estado HTTP 201 (Creado).
   */
  @PostMapping
  public ResponseEntity<Mensajes> crearMensaje(@RequestBody Mensajes mensaje) {
    Mensajes nuevoMensaje = mensajeServicio.crearMensaje(mensaje);
    return new ResponseEntity<>(nuevoMensaje, HttpStatus.CREATED);
  }

  /**
   * Recupera un mensaje por su ID.
   * GET /api/v1/mensaje/{id}
   * 
   * @param id El ID del mensaje a recuperar.
   * @return ResponseEntity con el mensaje y el estado HTTP 200 (OK), o 404 (No
   *         Encontrado) si no se encuentra.
   */
  @GetMapping("/{id}")
  public ResponseEntity<Mensajes> obtenerMensaje(@PathVariable Long id) {
    try {
      Mensajes mensaje = mensajeServicio.obtenerMensajePorId(id);
      return new ResponseEntity<>(mensaje, HttpStatus.OK);
    } catch (RuntimeException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Recupera todos los mensajes.
   * GET /api/v1/mensaje
   * 
   * @return ResponseEntity con una lista de todos los mensajes y el estado HTTP
   *         200 (OK).
   */
  @GetMapping
  public ResponseEntity<Map<String, Object>> obtenerMensajes() {
    List<Mensajes> mensajes = mensajeServicio.obtenerTodosLosMensajes();
    Map<String, Object> response = new HashMap<>();
    response.put("mensajes", mensajes);
    response.put("total", mensajes.size());
    response.put("message", "Listado de mensajes obtenido correctamente");
    response.put("status", "success");
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Recupera todos los mensajes no vistos.
   * GET /api/v1/mensaje/no-vistos
   * 
   * @return ResponseEntity con una lista de mensajes no vistos y el estado HTTP
   *         200 (OK).
   */
  @GetMapping("/no-vistos")
  public ResponseEntity<Map<String, Object>> obtenerMensajesNoVistos() {
    List<Mensajes> mensajes = mensajeServicio.findByVistoFalse();
    Map<String, Object> response = new HashMap<>();
    response.put("mensajes", mensajes);
    response.put("total", mensajes.size());
    response.put("message", "Listado de mensajes no vistos obtenido correctamente");
    response.put("status", "success");
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Marca un mensaje como visto.
   * PUT /api/v1/mensaje/{id}/marcar-visto
   * 
   * @param id El ID del mensaje a marcar como visto.
   * @return ResponseEntity con el mensaje actualizado y el estado HTTP 200 (OK),
   *         o 404 (No Encontrado) si el mensaje no existe.
   */
  @PutMapping("/{id}/marcar-visto")
  public ResponseEntity<Mensajes> marcarMensajeComoVisto(@PathVariable Long id) {
    try {
      Mensajes mensaje = mensajeServicio.marcarComoVisto(id);
      return new ResponseEntity<>(mensaje, HttpStatus.OK);
    } catch (RuntimeException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Elimina un mensaje por su ID.
   * DELETE /api/v1/mensaje/{id}
   * 
   * @param id El ID del mensaje a eliminar.
   * @return ResponseEntity con el estado HTTP 204 (Sin Contenido).
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminarMensaje(@PathVariable Long id) {
    try {
      mensajeServicio.eliminarMensaje(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (RuntimeException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}