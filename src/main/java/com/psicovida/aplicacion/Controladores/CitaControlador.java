package com.psicovida.aplicacion.Controladores;

import com.psicovida.aplicacion.Modelos.CitaModelo;
import com.psicovida.aplicacion.Modelos.CitaRequestDTO;
import com.psicovida.aplicacion.Modelos.PacienteModelo;
import com.psicovida.aplicacion.Servicios.CitaServicio;
import com.psicovida.aplicacion.Servicios.PacienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/cita")
public class CitaControlador {

  @Autowired
  private CitaServicio citaServicio;

  @Autowired
  private PacienteServicio pacienteServicio;

  @PostMapping
  public ResponseEntity<?> crearCita(@RequestBody CitaRequestDTO citaRequest) {
    try {
      PacienteModelo paciente = pacienteServicio.buscarPorDocumento(citaRequest.getDocumentoPaciente());
      if (paciente == null) {
        return new ResponseEntity<>("Paciente no encontrado", HttpStatus.NOT_FOUND);
      }
      CitaModelo cita = new CitaModelo();
      cita.setDoctor(new com.psicovida.aplicacion.Modelos.DoctorModelo());
      cita.getDoctor().setIdDoctor(citaRequest.getIdDoctor());
      cita.setPaciente(paciente);
      cita.setFecha(LocalDate.parse(citaRequest.getFecha()));
      cita.setHoraInicio(LocalTime.parse(citaRequest.getHoraInicio()));
      cita.setHoraFin(LocalTime.parse(citaRequest.getHoraFin()));
      cita.setEstado(citaRequest.getEstado());
      CitaModelo nuevaCita = citaServicio.crearCita(cita);
      return new ResponseEntity<>(nuevaCita, HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/tiene-cita")
  public ResponseEntity<?> tieneCitaAgendada(@RequestBody String documentoPaciente) {
    List<CitaModelo> citas = (List<CitaModelo>) citaServicio.obtenerCitas();
    for (CitaModelo cita : citas) {
      if (cita.getPaciente() != null && cita.getPaciente().getNumeroDocumento() != null &&
          cita.getPaciente().getNumeroDocumento().trim().equals(documentoPaciente.replaceAll("\"", "").trim())) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("tieneCita", true);
        Map<String, Object> citaSegura = new HashMap<>();
        citaSegura.put("nombrePaciente",
            cita.getPaciente().getPrimerNombre() + " " + cita.getPaciente().getPrimerApellido());
        citaSegura.put("nombreDoctor", cita.getDoctor().getPrimerNombre() + " " + cita.getDoctor().getPrimerApellido());
        citaSegura.put("fecha", cita.getFecha());
        citaSegura.put("horaInicio", cita.getHoraInicio());
        citaSegura.put("horaFin", cita.getHoraFin());
        citaSegura.put("estado", cita.getEstado());
        respuesta.put("cita", citaSegura);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
      }
    }
    Map<String, Object> respuesta = new HashMap<>();
    respuesta.put("tieneCita", false);
    respuesta.put("cita", null);
    return new ResponseEntity<>(respuesta, HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<Iterable<CitaModelo>> obtenerCitas() {
    Iterable<CitaModelo> citas = citaServicio.obtenerCitas();
    return new ResponseEntity<>(citas, HttpStatus.OK);
  }

  @GetMapping("/tiene-cita/{cedulaPaciente}")
  public ResponseEntity<?> tieneCitaAgendadaPorCedula(@PathVariable String cedulaPaciente) {
    List<CitaModelo> citas = (List<CitaModelo>) citaServicio.obtenerCitas();
    for (CitaModelo cita : citas) {
      if (cita.getPaciente() != null && cita.getPaciente().getNumeroDocumento() != null &&
          cita.getPaciente().getNumeroDocumento().trim().equals(cedulaPaciente.trim())) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("tieneCita", true);
        Map<String, Object> citaSegura = new HashMap<>();
        citaSegura.put("nombrePaciente",
            cita.getPaciente().getPrimerNombre() + " " + cita.getPaciente().getPrimerApellido());
        citaSegura.put("nombreDoctor", cita.getDoctor().getPrimerNombre() + " " + cita.getDoctor().getPrimerApellido());
        citaSegura.put("fecha", cita.getFecha());
        citaSegura.put("horaInicio", cita.getHoraInicio());
        citaSegura.put("horaFin", cita.getHoraFin());
        citaSegura.put("estado", cita.getEstado());
        respuesta.put("cita", citaSegura);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
      }
    }
    Map<String, Object> respuesta = new HashMap<>();
    respuesta.put("tieneCita", false);
    respuesta.put("cita", null);
    return new ResponseEntity<>(respuesta, HttpStatus.OK);
  }

  @PutMapping("/confirmar/{idCita}")
  public ResponseEntity<?> confirmarCita(@PathVariable Long idCita) {
    try {
      CitaModelo citaActualizada = citaServicio.confirmarCita(idCita);
      if (citaActualizada == null) {
        return new ResponseEntity<>("Cita no encontrada", HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(citaActualizada, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/cancelar/{idCita}")
  public ResponseEntity<?> cancelarCita(@PathVariable Long idCita) {
    try {
      CitaModelo citaActualizada = citaServicio.cancelarCita(idCita);
      if (citaActualizada == null) {
        return new ResponseEntity<>("Cita no encontrada", HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(citaActualizada, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminarCita(@PathVariable Long id) {
    citaServicio.eliminarCita(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}