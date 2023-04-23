package deronzier.remi.reportsmicroservice.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This interface defines the proxy to the notes microservice
 *
 * @author Rémi Deronzier
 */
@FeignClient(name = "notes-microservice", url = "${notes-microservice.url}")
public interface NotesMicroserviceProxy {
    @GetMapping("/notes/count-notes-with-triggering-terms/patient/{patientId}")
    public long countByPatientIdWithContentContainingTriggeringTerms(@PathVariable long patientId,
            @RequestParam List<String> keywords);
}
