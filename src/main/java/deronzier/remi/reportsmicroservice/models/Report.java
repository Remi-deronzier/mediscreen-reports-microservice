package deronzier.remi.reportsmicroservice.models;

import lombok.Data;

/**
 * This class represents a report entity for a patient.
 * 
 * @author Rémi Deronzier
 */
@Data
public class Report {
    private Patient patient;
    private RiskLevel riskLevel;
}
