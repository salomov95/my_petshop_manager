package com.ssdev.mypet.domain.appointments;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller @RequestMapping
public class AppointmentsController {
  private final AppointmentsService service;
  private final Logger logger;

  @DateTimeFormat(pattern="yyyy-MM-dd")
  private final LocalDate TODAY = LocalDate.now();

  public AppointmentsController(AppointmentsService service) {
    this.service = service;
    this.logger = LoggerFactory.getLogger(AppointmentsController.class);
  }

  @GetMapping("/")
  public String listAppointments(Model model, @RequestParam Optional<LocalDate> filter) {
    LocalDate filter_date = filter.isPresent() ? filter.get() : TODAY;

    model.addAttribute("filter_date", filter_date);
    List<Appointment> appointments = this.service.listAppointments(filter_date);

    List<Appointment> morning = appointments.stream()
      .filter(a->a.getDueTime().isBefore(LocalTime.of(12, 00)))
      .collect(Collectors.toList());

    List<Appointment> afternoon = appointments.stream()
      .filter(a->(
        a.getDueTime().isAfter(LocalTime.of(12,00)) &&
        a.getDueTime().isBefore(LocalTime.of(18,00))
      ))
      .collect(Collectors.toList());

    List<Appointment> night = appointments.stream()
      .filter(a->(
        a.getDueTime().isAfter(LocalTime.of(18,00)) &&
        a.getDueTime().isBefore(LocalTime.of(22,00))
      ))
      .collect(Collectors.toList());

    model.addAttribute("morning", morning);
    model.addAttribute("afternoon", afternoon);
    model.addAttribute("night", night);

    CreateAppointmentDto dto = new CreateAppointmentDto("", "", "", TODAY, LocalTime.now(), "");
    model.addAttribute("dto", dto);

    return "index";
  }

  @PostMapping(path={"/appointments"}, consumes={MediaType.APPLICATION_FORM_URLENCODED_VALUE})
  public String createAppointment(CreateAppointmentDto dto) {
    this.service.createAppointment(dto);
    return "redirect:/";
  }

  @PostMapping("/appointments/{id}/cancel")
  public String cancelAppointments(@PathVariable String id) {
    try {
      this.service.cancelAppointment(id);
    } catch(Exception e) {
      logger.error("[ERROR] " + e.getMessage());
      return "redirect:/not-found";
    }

    return "redirect:/";
  }
}
