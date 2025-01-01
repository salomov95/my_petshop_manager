package com.ssdev.mypet.domain.appointments;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentsRepository
  extends JpaRepository<Appointment, String> {
    List<Appointment> findByDueDate(LocalDate dueDate);
}
