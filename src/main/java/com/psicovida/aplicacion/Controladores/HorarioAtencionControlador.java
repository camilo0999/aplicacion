package com.psicovida.aplicacion.Controladores;

import com.psicovida.aplicacion.Modelos.HorarioAtencionModelo;
import com.psicovida.aplicacion.Servicios.HorarioAtencionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/horario-atencion")
public class HorarioAtencionControlador {

  @Autowired
  private HorarioAtencionServicio horarioAtencionServicio;

  @PostMapping
  public ResponseEntity<HorarioAtencionModelo> crearHorario(@RequestBody HorarioAtencionModelo horario) {
    HorarioAtencionModelo nuevo = horarioAtencionServicio.crearHorario(horario);
    return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
  }

  @GetMapping("/doctor/{idDoctor}")
  public ResponseEntity<List<HorarioAtencionModelo>> listarHorariosPorDoctor(@PathVariable Long idDoctor) {
    List<HorarioAtencionModelo> horarios = horarioAtencionServicio.listarHorariosPorDoctor(idDoctor);
    return new ResponseEntity<>(horarios, HttpStatus.OK);
  }

  @GetMapping("/doctor/{idDoctor}/dia/{diaSemana}")
  public ResponseEntity<List<HorarioAtencionModelo>> listarHorariosPorDoctorYDia(@PathVariable Long idDoctor,
      @PathVariable String diaSemana) {
    List<HorarioAtencionModelo> horarios = horarioAtencionServicio.listarHorariosPorDoctorYDia(idDoctor, diaSemana);
    return new ResponseEntity<>(horarios, HttpStatus.OK);
  }

  @PutMapping("/{idHorario}")
  public ResponseEntity<HorarioAtencionModelo> actualizarHorario(@PathVariable Long idHorario,
      @RequestBody HorarioAtencionModelo horario) {
    HorarioAtencionModelo actualizado = horarioAtencionServicio.actualizarHorario(idHorario, horario);
    return new ResponseEntity<>(actualizado, HttpStatus.OK);
  }

  @DeleteMapping("/{idHorario}")
  public ResponseEntity<Void> eliminarHorario(@PathVariable Long idHorario) {
    horarioAtencionServicio.eliminarHorario(idHorario);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/disponibilidad/{idDoctor}")
  public ResponseEntity<List<String>> obtenerHorariosDisponibles(@PathVariable Long idDoctor,
      @RequestParam String fecha) {
    List<String> disponibles = horarioAtencionServicio.obtenerHorariosDisponibles(idDoctor, fecha);
    return new ResponseEntity<>(disponibles, HttpStatus.OK);
  }
}