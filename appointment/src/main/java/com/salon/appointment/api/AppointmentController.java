package com.salon.appointment.api;
import io.restassured.RestAssured;
import com.salon.appointment.repo.model.Appointment;
import com.salon.appointment.service.AppointmentService;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import net.minidev.json.JSONObject;
import javax.validation.Valid;
import java.util.List;

@RestController
public class AppointmentController {
    private final AppointmentService appointmentService;

    private final RestTemplate restTemplate;

    @GetMapping("/")
    public String appointment() throws JsonParseException
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "Hello,it is appointment service");
        jsonObject.put("message-2", restTemplate.exchange("http://localhost:8084/", HttpMethod.GET, null, String.class).getBody());
        jsonObject.put("message-3", restTemplate.exchange("http://localhost:8082/", HttpMethod.GET, null, String.class).getBody());
        return jsonObject.toString();
    }
    @Autowired
    public AppointmentController(AppointmentService appointmentService, RestTemplate restTemplate)
    {
        this.appointmentService = appointmentService;
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = "/appointment")
    public ResponseEntity<List<Appointment>> getAppointments()
    {
        return ResponseEntity.ok(appointmentService.getAppointments());
    }

    @PostMapping(value = "/appointment")
    public ResponseEntity<Appointment> postAppointments(@Valid @RequestBody Appointment newAppointment) {
        return ResponseEntity.ok(appointmentService.saveAppointments(newAppointment));
    }
    @GetMapping(value = "/appointment/{id_appointment}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable Long id_appointment) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id_appointment));
    }

    @PutMapping(value = "/appointment/{id_appointment}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id_appointment, @Valid @RequestBody Appointment updatedAppointment) {
        return ResponseEntity.ok(appointmentService.updateAppointmentById(id_appointment, updatedAppointment));
    }

    @DeleteMapping(value = "/appointment/{id_appointment}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id_appointment) {
        return ResponseEntity.ok(appointmentService.deleteAppointmentById(id_appointment));
    }
}
