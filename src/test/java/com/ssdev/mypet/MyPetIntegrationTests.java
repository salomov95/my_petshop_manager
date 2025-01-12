package com.ssdev.mypet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.MultiValueMap;

import com.ssdev.mypet.domain.appointments.AppointmentsController;
import com.ssdev.mypet.domain.appointments.AppointmentsRepository;
import com.ssdev.mypet.domain.appointments.AppointmentsService;
import com.ssdev.mypet.domain.appointments.CreateAppointmentDto;

@WebMvcTest(controllers={AppointmentsController.class})
class MypetIntegrationTests {
  @Autowired
  MockMvc mvc;

  @MockitoBean
  public AppointmentsRepository appointmentsRepository;

  @MockitoBean
  public AppointmentsService appointmentsService = new AppointmentsService(appointmentsRepository);

  String mockAppointmentId = "65782408-4e28-4c1f-ad3d-43c1598b95c2";
  CreateAppointmentDto mockDto = new CreateAppointmentDto(
    "J. Doe",
    "Caramelo",
    "Tosa",
    LocalDate.now(),
    LocalTime.now(),
    "(55) 5-5555-5555"
  );

  @BeforeEach
  void setupEach() {
    when(appointmentsService.createAppointment(mockDto))
      .thenReturn(mockAppointmentId);

    when(appointmentsService.listAppointments(any(LocalDate.class)))
      .thenReturn(List.of());
  }

	@Test
	void TEST_ROOT_PATH_SHOULD_BE_UNAUTHORIZED() throws Exception {
    mvc.perform(get("/").contentType("application/json")).andExpect(status().isUnauthorized());
	}

  @Test @Disabled("It's Returning Status Code 403 Where It Shouldn't")
  @WithMockUser(username="salomovs95", password="12345")
  void TEST_SHOULD_CREATE_APPOINTMENT() throws Exception {
    Map<String, String> paramsMap = new HashMap<>();

    paramsMap.put("petTutorName", mockDto.petTutorName());
    paramsMap.put("petName", mockDto.petName());
    paramsMap.put("descriptor", mockDto.descriptor());
    paramsMap.put("dueDate", ""+mockDto.dueDate());
    paramsMap.put("dueTime", ""+mockDto.dueTime());
    paramsMap.put("contactPhone", mockDto.contactPhone());

    MockHttpServletRequestBuilder req = post("/appointments")
      .contentType(MediaType.APPLICATION_FORM_URLENCODED)
      .params(MultiValueMap.fromSingleValue(paramsMap));

    mvc.perform(req).andExpect(status().isForbidden());
  }
}
