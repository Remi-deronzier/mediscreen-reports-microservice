package deronzier.remi.reportsmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import deronzier.remi.reportsmicroservice.exceptions.PatientNotFoundException;
import deronzier.remi.reportsmicroservice.models.Patient;
import deronzier.remi.reportsmicroservice.services.PatientService;

/**
 * This class is the controller of the reports microservice
 * 
 * @author Rémi Deronzier
 */
@RestController
public class ReportController {

    @Autowired
    private PatientService patientService;

    /**
     * @param id
     * @return Patient
     * @throws PatientNotFoundException
     */
    @GetMapping("/patients/{id}")
    public Patient getPatientById(@PathVariable long id) throws PatientNotFoundException {
        return patientService.find(id);
    }

}