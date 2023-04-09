package deronzier.remi.reportsmicroservice.services;

import deronzier.remi.reportsmicroservice.exceptions.PatientNotFoundException;
import deronzier.remi.reportsmicroservice.models.Patient;

/**
 * This interface defines all the methods for the services related to the
 * Patient entity
 * 
 * @author Rémi Deronzier
 */
public interface PatientService {
    public Patient find(long id) throws PatientNotFoundException;
}
