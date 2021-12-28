package com.salon.servicetype.service;
import com.salon.servicetype.exception.ServiceTypeNotFoundException;
import com.salon.servicetype.repo.ServiceTypeRepository;

import com.salon.servicetype.repo.model.ServiceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ServiceTypeService {

    private final ServiceTypeRepository serviceTypeRepository;

    public ServiceTypeService(ServiceTypeRepository serviceTypeRepository) {
        this.serviceTypeRepository = serviceTypeRepository;
    }

    public List<ServiceType> getServiceTypes() {
        return serviceTypeRepository.findAll();
    }

    public ServiceType saveServiceTypes(ServiceType newServiceType) {
        return serviceTypeRepository.save(newServiceType);
    }

    public ServiceType getServiceTypeById(Long id_service) {
        Optional<ServiceType> servicetype = serviceTypeRepository.findById(id_service);
        if (servicetype.isPresent()) {
            log.info("service: {}", servicetype.get());
            return servicetype.get();
        }
        throw new ServiceTypeNotFoundException();
    }
    public ServiceType updateServiceTypeById(Long id_service, ServiceType updatedServiceType) {
        Optional<ServiceType> servicetype = serviceTypeRepository.findById(id_service);
        if (servicetype.isPresent()) {
            ServiceType oldServiceType = servicetype.get();
            log.info("service: {}", oldServiceType);
            updateServiceType(oldServiceType, updatedServiceType);
            return serviceTypeRepository.save(oldServiceType);
        }
        throw new ServiceTypeNotFoundException();
    }

    private void updateServiceType(ServiceType oldServiceType, ServiceType updatedServiceType) {
        oldServiceType.setGender(updatedServiceType.getGender());
        oldServiceType.setServiceType(updatedServiceType.getServiceType());
        oldServiceType.setServicePrice(updatedServiceType.getServicePrice());
    }

    public String deleteServiceTypeById(Long id_service) {
        serviceTypeRepository.deleteById(id_service);
        return "Service was successfully deleted!";
    }
}



