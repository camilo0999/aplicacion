package com.psicovida.aplicacion.Servicios.Imp;

import com.psicovida.aplicacion.Modelos.HorarioAtencionModelo;
import com.psicovida.aplicacion.Repositorios.HorarioAtencionRepositorio;
import com.psicovida.aplicacion.Servicios.HorarioAtencionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import com.psicovida.aplicacion.Modelos.CitaModelo;
import com.psicovida.aplicacion.Repositorios.CitaRepositorio;

@Service
public class HorarioAtencionServicioImp implements HorarioAtencionServicio {

  @Autowired
  private HorarioAtencionRepositorio horarioAtencionRepositorio;

  @Autowired
  private CitaRepositorio citaRepositorio;

  @Override
  public HorarioAtencionModelo crearHorario(HorarioAtencionModelo horario) {
    return horarioAtencionRepositorio.save(horario);
  }

  @Override
  public List<HorarioAtencionModelo> listarHorariosPorDoctor(Long idDoctor) {
    return horarioAtencionRepositorio.findByDoctorIdDoctor(idDoctor);
  }

  @Override
  public List<HorarioAtencionModelo> listarHorariosPorDoctorYDia(Long idDoctor, String diaSemana) {
    return horarioAtencionRepositorio.findByDoctorIdDoctorAndDiaSemana(idDoctor, diaSemana.toUpperCase());
  }

  @Override
  public HorarioAtencionModelo actualizarHorario(Long idHorario, HorarioAtencionModelo horario) {
    horario.setIdHorario(idHorario);
    return horarioAtencionRepositorio.save(horario);
  }

  @Override
  public void eliminarHorario(Long idHorario) {
    horarioAtencionRepositorio.deleteById(idHorario);
  }

  @Override
  public List<String> obtenerHorariosDisponibles(Long idDoctor, String fecha) {
    LocalDate localDate = LocalDate.parse(fecha);
    DayOfWeek diaSemana = localDate.getDayOfWeek();
    String nombreDia = diaSemana.getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase();
    List<HorarioAtencionModelo> horarios = listarHorariosPorDoctorYDia(idDoctor, nombreDia);
    List<String> bloquesDisponibles = new ArrayList<>();
    for (HorarioAtencionModelo horario : horarios) {
      LocalTime hora = horario.getHoraInicio();
      while (hora.plusHours(1).isBefore(horario.getHoraFin()) || hora.plusHours(1).equals(horario.getHoraFin())) {
        bloquesDisponibles.add(hora.toString());
        hora = hora.plusHours(1);
      }
    }
    // Filtrar bloques ocupados por citas
    List<CitaModelo> citas = citaRepositorio.findByDoctorIdDoctorAndFecha(idDoctor, localDate);
    for (CitaModelo cita : citas) {
      String horaOcupada = cita.getHoraInicio().toString();
      bloquesDisponibles.remove(horaOcupada);
    }
    return bloquesDisponibles;
  }
}