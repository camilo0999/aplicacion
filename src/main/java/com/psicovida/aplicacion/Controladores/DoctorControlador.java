package com.psicovida.aplicacion.Controladores;

import com.psicovida.aplicacion.Modelos.DoctorModelo;
import com.psicovida.aplicacion.Servicios.DoctorServicio;
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
@RequestMapping("/api/v1/doctor")
public class DoctorControlador {

  @Autowired
  private DoctorServicio doctorServicio;

  @PostMapping
  public ResponseEntity<DoctorModelo> crearDoctor(@RequestBody DoctorModelo doctor) {
    DoctorModelo nuevoDoctor = doctorServicio.crearDoctor(doctor);
    return new ResponseEntity<>(nuevoDoctor, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DoctorModelo> obtenerDoctor(@PathVariable Long id) {
    DoctorModelo doctor = doctorServicio.obtenerDoctor(id);
    if (doctor != null) {
      return new ResponseEntity<>(doctor, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/cedula/{numeroDocumento}")
  public ResponseEntity<DoctorModelo> obtenerDoctorPorCedula(@PathVariable String numeroDocumento) {
    DoctorModelo doctor = doctorServicio.obtenerDoctorPorNumeroDocumento(numeroDocumento);
    if (doctor != null) {
      return new ResponseEntity<>(doctor, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping
  public ResponseEntity<Map<String, Object>> obtenerDoctores(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    Pageable paging = PageRequest.of(page, size);
    Page<DoctorModelo> pageDoctores = doctorServicio.obtenerDoctoresPaginados(paging);
    Map<String, Object> response = new HashMap<>();
    response.put("doctores", pageDoctores.getContent());
    response.put("currentPage", pageDoctores.getNumber());
    response.put("totalItems", pageDoctores.getTotalElements());
    response.put("totalPages", pageDoctores.getTotalPages());
    response.put("message", "Listado paginado de doctores obtenido correctamente");
    response.put("status", "success");
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<DoctorModelo> actualizarDoctor(@RequestBody DoctorModelo doctor) {
    DoctorModelo doctorActualizado = doctorServicio.actualizarDoctor(doctor);
    if (doctorActualizado != null) {
      return new ResponseEntity<>(doctorActualizado, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/cedula/{numeroDocumento}")
  public ResponseEntity<DoctorModelo> actualizarDoctorPorCedula(@PathVariable String numeroDocumento,
      @RequestBody DoctorModelo doctor) {
    DoctorModelo doctorActualizado = doctorServicio.actualizarDoctorPorNumeroDocumento(numeroDocumento, doctor);
    if (doctorActualizado != null) {
      return new ResponseEntity<>(doctorActualizado, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminarDoctor(@PathVariable Long id) {
    doctorServicio.eliminarDoctor(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}