package deronzier.remi.reportsmicroservice.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import deronzier.remi.reportsmicroservice.exceptions.PatientNotFoundException;
import deronzier.remi.reportsmicroservice.models.Patient;
import deronzier.remi.reportsmicroservice.proxies.PatientsMicroserviceProxy;
import deronzier.remi.reportsmicroservice.services.PatientService;

/**
 * This class defines all the business logic for the Patient entity
 * 
 * @author Rémi Deronzier
 */
@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientsMicroserviceProxy proxy;

    @Value("${label.patient}")
    private String patient;

    @Value("${label.not-found}")
    private String notFound;

    /**
     * @param id
     * @return String
     */
    private String patientNotFoundMessage(long id) {
        return patient + " " + id + " " + notFound;
    }

    /**
     * @param id
     * @return Patient
     * @throws PatientNotFoundException
     */
    @Override
    public Patient find(long id) throws PatientNotFoundException {
        return proxy.getPatientById(id)
                .orElseThrow(() -> new PatientNotFoundException(patientNotFoundMessage(id)));
    }

}
