package com.psicovida.aplicacion.Controladores;

import com.psicovida.aplicacion.Modelos.CitaModelo;
import com.psicovida.aplicacion.Servicios.CitaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cita")
public class CitaControlador {

  @Autowired
  private CitaServicio citaServicio;

  @PostMapping
  public ResponseEntity<CitaModelo> crearCita(@RequestBody CitaModelo cita) {
    CitaModelo nuevaCita = citaServicio.crearCita(cita);
    return new ResponseEntity<>(nuevaCita, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<Iterable<CitaModelo>> obtenerCitas() {
    Iterable<CitaModelo> citas = citaServicio.obtenerCitas();
    return new ResponseEntity<>(citas, HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<CitaModelo> actualizarCita(@RequestBody CitaModelo cita) {
    CitaModelo citaActualizada = citaServicio.actualizarCita(cita);
    return new ResponseEntity<>(citaActualizada, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminarCita(@PathVariable Long id) {
    citaServicio.eliminarCita(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}