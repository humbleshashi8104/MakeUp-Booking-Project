package modules.servicecatalog.controller;

import modules.servicecatalog.dto.request.ServiceRequest;
import modules.servicecatalog.dto.response.ServiceResponse;
import modules.servicecatalog.service.ServiceCatalogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceCatalogService service;

    public ServiceController(ServiceCatalogService service) {
        this.service = service;
    }

    @PostMapping
    public ServiceResponse createService(@RequestBody ServiceRequest request) {
        return service.createService(request);
    }

    @GetMapping
    public List<ServiceResponse> getAllServices() {
        return service.getAllServices();
    }
}