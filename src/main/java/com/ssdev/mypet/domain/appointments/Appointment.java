package com.ssdev.mypet.domain.appointments;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name="tb_appointments") @Entity
public class Appointment {
  @Id @GeneratedValue(strategy=GenerationType.UUID)
  private String id;

  private String descriptor;

  @Column(name="pet_tutor_name")
  private String petTutorName;

  @Column(name="pet_name")
  private String petName;
  
  @Column(name="due_date")
  private LocalDate dueDate;

  @Column(name="due_time")
  private LocalTime dueTime;

  @Column(name="contact_phone")
  private String contactPhone;

  public Appointment(CreateAppointmentDto dto) {
    descriptor = dto.descriptor();
    petTutorName = dto.petTutorName();
    petName = dto.petName();
    dueDate = dto.dueDate();
    dueTime = dto.dueTime();
    contactPhone = dto.contactPhone();
  }
}
