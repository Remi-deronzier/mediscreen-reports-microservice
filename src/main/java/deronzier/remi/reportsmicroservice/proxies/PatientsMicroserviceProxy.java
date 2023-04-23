package deronzier.remi.reportsmicroservice.proxies;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import deronzier.remi.reportsmicroservice.models.Patient;

/**
 * This interface is the proxy to the patient microservice
 *
 * @author Rémi Deronzier
 */
@FeignClient(name = "patients-microservice", url = "${patients-microservice.url}")
public interface PatientsMicroserviceProxy {
    @GetMapping("/patients/{id}")
    public Optional<Patient> getPatientById(@PathVariable long id);
}
