package deronzier.remi.reportsmicroservice.services;

import java.net.MalformedURLException;

import deronzier.remi.reportsmicroservice.models.Patient;
import deronzier.remi.reportsmicroservice.models.RiskLevel;

/**
 * This interface is used to generate a PDF file for the patient report.
 * 
 * @author Rémi Deronzier
 */
public interface PdfGeneratorService {
    public byte[] generatePDF(Patient patient, RiskLevel riskLevel) throws MalformedURLException;
}
