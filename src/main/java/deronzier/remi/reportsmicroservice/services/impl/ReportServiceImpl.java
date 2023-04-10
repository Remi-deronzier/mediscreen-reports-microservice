package deronzier.remi.reportsmicroservice.services.impl;

import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import deronzier.remi.reportsmicroservice.exceptions.PatientNotFoundException;
import deronzier.remi.reportsmicroservice.models.Patient;
import deronzier.remi.reportsmicroservice.models.Report;
import deronzier.remi.reportsmicroservice.models.RiskLevel;
import deronzier.remi.reportsmicroservice.models.Sex;
import deronzier.remi.reportsmicroservice.services.NoteService;
import deronzier.remi.reportsmicroservice.services.PatientService;
import deronzier.remi.reportsmicroservice.services.ReportService;
import deronzier.remi.reportsmicroservice.utils.Constants;

/**
 * This class defines all the business logic for the Report entity
 * 
 * @author Rémi Deronzier
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private PatientService patientService;

    @Autowired
    private NoteService noteService;

    @Autowired
    private PdfGeneratorServiceImpl pdfService;

    /**
     * @param patientId
     * @return Report
     * @throws PatientNotFoundException
     */
    @Override
    public Report generateReport(long patientId) throws PatientNotFoundException {
        Patient patient = getPatient(patientId);
        Report report = new Report();
        report.setPatient(patient);
        report.setRiskLevel(getRiskLevel(patientId));
        return report;
    }

    /**
     * @param patientId
     * @return byte[]
     * @throws MalformedURLException
     * @throws PatientNotFoundException
     */
    @Override
    public byte[] generatePdfReport(long patientId) throws MalformedURLException, PatientNotFoundException {
        Patient patient = getPatient(patientId);
        RiskLevel riskLevel = getRiskLevel(patientId);
        return pdfService.generatePDF(patient, riskLevel);
    }

    /**
     * @param patientId
     * @return Patient
     * @throws PatientNotFoundException
     */
    private Patient getPatient(long patientId) throws PatientNotFoundException {
        return patientService.find(patientId);
    }

    /**
     * @param patientId
     * @return long
     */
    private long getPatientCountNotesWithContentContainingTriggeringTerms(long patientId) {
        return noteService.countByPatientIdWithContentContainingTriggeringTerms(patientId, Constants.TRIGGERING_TERMS);
    }

    /**
     * @param patientId
     * @return RiskLevel
     * @throws PatientNotFoundException
     */
    private RiskLevel getRiskLevel(long patientId) throws PatientNotFoundException {
        long countNotesWithTriggeringTerms = getPatientCountNotesWithContentContainingTriggeringTerms(patientId);
        Patient patient = getPatient(patientId);

        if (isPatientSafe(patient, countNotesWithTriggeringTerms)) {
            return RiskLevel.NONE;
        }
        if (isPatientBorderline(patient, countNotesWithTriggeringTerms)) {
            return RiskLevel.BORDERLINE;
        }
        if (isPatientInDanger(patient, countNotesWithTriggeringTerms)) {
            return RiskLevel.IN_DANGER;
        }
        if (isPatientInEarlyOnset(patient, countNotesWithTriggeringTerms)) {
            return RiskLevel.EARLY_ONSET;
        }
        return RiskLevel.NONE;
    }

    /**
     * @param patient
     * @param countNotesWithTriggeringTerms
     * @return boolean
     */
    private boolean isPatientSafe(Patient patient, long countNotesWithTriggeringTerms) {
        return countNotesWithTriggeringTerms == 0;
    }

    /**
     * @param patient
     * @param countNotesWithTriggeringTerms
     * @return boolean
     */
    private boolean isPatientBorderline(Patient patient, long countNotesWithTriggeringTerms) {
        return countNotesWithTriggeringTerms == 2 && patient.getAge() >= 30;
    }

    /**
     * @param patient
     * @param countNotesWithTriggeringTerms
     * @return boolean
     */
    private boolean isPatientInDanger(Patient patient, long countNotesWithTriggeringTerms) {
        return (patient.getSex() == Sex.M && patient.getAge() < 30 && countNotesWithTriggeringTerms == 3) ||
                (patient.getSex() == Sex.F && patient.getAge() < 30 && countNotesWithTriggeringTerms == 4) ||
                (patient.getAge() >= 30 && countNotesWithTriggeringTerms == 6);
    }

    /**
     * @param patient
     * @param countNotesWithTriggeringTerms
     * @return boolean
     */
    private boolean isPatientInEarlyOnset(Patient patient, long countNotesWithTriggeringTerms) {
        return (patient.getSex() == Sex.M && patient.getAge() < 30 && countNotesWithTriggeringTerms == 5) ||
                (patient.getSex() == Sex.F && patient.getAge() < 30 && countNotesWithTriggeringTerms == 7) ||
                (patient.getAge() >= 30 && countNotesWithTriggeringTerms >= 8);
    }

}
