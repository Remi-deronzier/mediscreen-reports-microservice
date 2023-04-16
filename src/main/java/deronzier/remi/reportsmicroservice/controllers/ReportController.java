package deronzier.remi.reportsmicroservice.controllers;

import java.net.MalformedURLException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import deronzier.remi.reportsmicroservice.exceptions.PatientNotFoundException;
import deronzier.remi.reportsmicroservice.models.Report;
import deronzier.remi.reportsmicroservice.services.ReportService;

/**
 * This class is the controller of the reports microservice
 * 
 * @author Rémi Deronzier
 */
@RestController
@RequestMapping("/reports")
@CrossOrigin(origins = "*")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * @param patientId
     * @return Report
     * @throws PatientNotFoundException
     */
    @GetMapping
    public Report getReport(@RequestParam long patientId) throws PatientNotFoundException {
        return reportService.generateReport(patientId);
    }

    /**
     * @param patientId
     * @return byte[]
     * @throws MalformedURLException
     * @throws PatientNotFoundException
     */
    @GetMapping("/pdf")
    public byte[] exportPdf(@RequestParam long patientId) throws MalformedURLException, PatientNotFoundException {
        byte[] byteArray = reportService.generatePdfReport(patientId);

        byte[] encodedBytes = Base64.getEncoder().encode(byteArray);
        return encodedBytes;
    }

}
