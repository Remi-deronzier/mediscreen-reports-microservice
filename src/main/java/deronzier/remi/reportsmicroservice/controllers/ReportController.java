package deronzier.remi.reportsmicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * @param patientId
     * @return Report
     * @throws PatientNotFoundException
     */
    @GetMapping("/{patientId}/report")
    public Report getReport(@PathVariable long patientId) throws PatientNotFoundException {
        return reportService.generateReport(patientId);
    }

}
