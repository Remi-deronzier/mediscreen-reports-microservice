package deronzier.remi.reportsmicroservice.services;

import java.net.MalformedURLException;

import deronzier.remi.reportsmicroservice.exceptions.PatientNotFoundException;
import deronzier.remi.reportsmicroservice.models.Report;

/**
 * This interface defines all the business logic for the Report entity
 * 
 * @author Rémi Deronzier
 */
public interface ReportService {
    public Report generateReport(long patientId) throws PatientNotFoundException;

    public byte[] generatePdfReport(long patientId) throws MalformedURLException, PatientNotFoundException;

}
