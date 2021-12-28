package com.salon.servicetype.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.salon.servicetype.repo.model.ServiceType;
import com.salon.servicetype.service.ServiceTypeService;
import org.springframework.web.client.RestTemplate;
import net.minidev.json.JSONObject;
import javax.validation.Valid;
import java.util.List;

@RestController
public class ServiceTypeController {

    private final ServiceTypeService serviceTypeService;
    private final RestTemplate restTemplate;

    @GetMapping(value="/")
    public String service() throws JsonParseException
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "Hello, it is type service");
        return jsonObject.toString();
    }

    @Autowired
    public ServiceTypeController(ServiceTypeService serviceTypeService, RestTemplate restTemplate)
    {
        this.serviceTypeService = serviceTypeService;
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = "/service")
    public ResponseEntity<List<ServiceType>> getServiceTypes() {
        return ResponseEntity.ok(serviceTypeService.getServiceTypes());
    }

    @PostMapping(value = "/service")
    public ResponseEntity<ServiceType> postServiceTypes(@Valid @RequestBody ServiceType newServiceType) {
        return ResponseEntity.ok(serviceTypeService.saveServiceTypes(newServiceType));
    }
    @GetMapping(value = "/service/{id_service}")
    public ResponseEntity<ServiceType> getServiceType(@PathVariable Long id_service) {
        return ResponseEntity.ok(serviceTypeService.getServiceTypeById(id_service));
    }

    @PutMapping(value = "/service/{id_service}")
    public ResponseEntity<ServiceType> updateServiceType(@PathVariable Long id_service, @Valid @RequestBody ServiceType updatedServiceType) {
        return ResponseEntity.ok(serviceTypeService.updateServiceTypeById(id_service, updatedServiceType));
    }

    @DeleteMapping(value = "/service/{id_service}")
    public ResponseEntity<String> deleteServiceType(@PathVariable Long id_service) {
        return ResponseEntity.ok(serviceTypeService.deleteServiceTypeById(id_service));
    }
}
