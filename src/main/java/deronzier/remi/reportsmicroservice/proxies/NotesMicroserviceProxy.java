package deronzier.remi.reportsmicroservice.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import deronzier.remi.reportsmicroservice.models.Note;

/**
 * This interface defines the proxy to the notes microservice
 * 
 * @author Rémi Deronzier
 */
@FeignClient(name = "notes-microservice", url = "localhost:8082")
public interface NotesMicroserviceProxy {
    @GetMapping(value = "/notes/patient/{patientId}")
    public Page<Note> findByPatientId(@PathVariable long patientId);
}
