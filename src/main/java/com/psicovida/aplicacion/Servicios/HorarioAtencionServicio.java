package com.psicovida.aplicacion.Servicios;

import com.psicovida.aplicacion.Modelos.HorarioAtencionModelo;

import java.util.List;

public interface HorarioAtencionServicio {
  HorarioAtencionModelo crearHorario(HorarioAtencionModelo horario);

  List<HorarioAtencionModelo> listarHorariosPorDoctor(Long idDoctor);

  List<HorarioAtencionModelo> listarHorariosPorDoctorYDia(Long idDoctor, String diaSemana);

  HorarioAtencionModelo actualizarHorario(Long idHorario, HorarioAtencionModelo horario);

  void eliminarHorario(Long idHorario);

  List<String> obtenerHorariosDisponibles(Long idDoctor, String fecha); // fecha en formato YYYY-MM-DD
}