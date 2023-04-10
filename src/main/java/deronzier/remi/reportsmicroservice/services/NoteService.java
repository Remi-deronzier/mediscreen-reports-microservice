package deronzier.remi.reportsmicroservice.services;

import java.util.List;

/**
 * This interface defines all the business logic for the Note entity
 * 
 * @author Rémi Deronzier
 */
public interface NoteService {
    public long countByPatientIdWithContentContainingTriggeringTerms(long patientId, List<String> keywords);
}
