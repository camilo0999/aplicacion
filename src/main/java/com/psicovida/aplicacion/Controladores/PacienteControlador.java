package com.psicovida.aplicacion.Controladores;

import com.psicovida.aplicacion.Modelos.PacienteModelo;
import com.psicovida.aplicacion.Servicios.PacienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/paciente")
public class PacienteControlador {

  @Autowired
  private PacienteServicio pacienteServicio;

  /**
   * Crea un nuevo paciente.
   * POST /api/v1/paciente
   * 
   * @param paciente El objeto paciente a crear.
   * @return ResponseEntity con el paciente creado y el estado HTTP 201 (Creado).
   */
  @PostMapping
  public ResponseEntity<PacienteModelo> crearPaciente(@RequestBody PacienteModelo paciente) {
    PacienteModelo nuevoPaciente = pacienteServicio.crearPaciente(paciente);
    return new ResponseEntity<>(nuevoPaciente, HttpStatus.CREATED);
  }

  /**
   * Recupera un paciente por su ID.
   * GET /api/v1/paciente/{id}
   * 
   * @param id El ID del paciente a recuperar.
   * @return ResponseEntity con el paciente y el estado HTTP 200 (OK), o 404 (No
   *         Encontrado) si no se encuentra.
   */
  @GetMapping("/{id}")
  public ResponseEntity<PacienteModelo> obtenerPaciente(@PathVariable String id) {
    PacienteModelo paciente = pacienteServicio.obtenerPaciente(id);
    if (paciente != null) {
      return new ResponseEntity<>(paciente, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Recupera todos los pacientes.
   * GET /api/v1/paciente
   * 
   * @return ResponseEntity con una lista de todos los pacientes y el estado HTTP
   *         200 (OK).
   */
  @GetMapping
  public ResponseEntity<Map<String, Object>> obtenerPacientes(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    Pageable paging = PageRequest.of(page, size);
    Page<PacienteModelo> pagePacientes = pacienteServicio.obtenerPacientesPaginados(paging);
    Map<String, Object> response = new HashMap<>();
    response.put("pacientes", pagePacientes.getContent());
    response.put("currentPage", pagePacientes.getNumber());
    response.put("totalItems", pagePacientes.getTotalElements());
    response.put("totalPages", pagePacientes.getTotalPages());
    response.put("message", "Listado paginado de pacientes obtenido correctamente");
    response.put("status", "success");
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Actualiza un paciente existente.
   * PUT /api/v1/paciente
   * 
   * @param paciente El objeto paciente con la información actualizada.
   * @return ResponseEntity con el paciente actualizado y el estado HTTP 200 (OK),
   *         o 404 (No Encontrado) si el paciente no existe.
   */
  @PutMapping
  public ResponseEntity<PacienteModelo> actualizarPaciente(@RequestBody PacienteModelo paciente) {
    PacienteModelo pacienteActualizado = pacienteServicio.actualizarPaciente(paciente);
    if (pacienteActualizado != null) {
      return new ResponseEntity<>(pacienteActualizado, HttpStatus.OK);
    } else {
      // Podrías manejar los casos en los que el paciente a actualizar no existe de
      // forma más explícita
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Elimina un paciente por su ID.
   * DELETE /api/v1/paciente/{id}
   * 
   * @param id El ID del paciente a eliminar.
   * @return ResponseEntity con el estado HTTP 204 (Sin Contenido).
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id) {
    pacienteServicio.eliminarPaciente(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}