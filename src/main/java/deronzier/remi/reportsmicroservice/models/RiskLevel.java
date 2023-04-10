package deronzier.remi.reportsmicroservice.models;

/**
 * This enum describes the 4 possible risk levels of a patient
 * 
 * @author Rémi Deronzier
 */
public enum RiskLevel {
    NONE("Aucun risque"),
    BORDERLINE("Borderline"),
    IN_DANGER("En danger"),
    EARLY_ONSET("Apparition précoce");

    private String label;

    private RiskLevel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
