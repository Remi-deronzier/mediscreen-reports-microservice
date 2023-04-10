package deronzier.remi.reportsmicroservice.services;

import java.net.MalformedURLException;

/**
 * This interface is used to generate a PDF file for the patient report.
 * 
 * @author Rémi Deronzier
 */
public interface PdfGeneratorService {
    public byte[] generatePDF() throws MalformedURLException;
}
