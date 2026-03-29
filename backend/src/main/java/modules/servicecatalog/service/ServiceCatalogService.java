package modules.servicecatalog.service;

import modules.servicecatalog.dto.request.ServiceRequest;
import modules.servicecatalog.dto.response.ServiceResponse;
import modules.servicecatalog.entity.ServiceEntity;
import modules.servicecatalog.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceCatalogService {

    private final ServiceRepository serviceRepository;

    public ServiceCatalogService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public ServiceResponse createService(ServiceRequest request) {

        ServiceEntity service = new ServiceEntity();
        service.setName(request.getName());
        service.setDescription(request.getDescription());
        service.setPrice(request.getPrice());
        service.setDuration(request.getDuration());

        ServiceEntity saved = serviceRepository.save(service);

        return new ServiceResponse(
                saved.getId(),
                saved.getName(),
                saved.getDescription(),
                saved.getPrice(),
                saved.getDuration()
        );
    }

    public List<ServiceResponse> getAllServices() {
        return serviceRepository.findAll()
                .stream()
                .map(s -> new ServiceResponse(
                        s.getId(),
                        s.getName(),
                        s.getDescription(),
                        s.getPrice(),
                        s.getDuration()
                ))
                .collect(Collectors.toList());
    }
}