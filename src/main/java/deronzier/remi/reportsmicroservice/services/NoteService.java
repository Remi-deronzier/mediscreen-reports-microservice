package deronzier.remi.reportsmicroservice.services;

import java.util.List;

import deronzier.remi.reportsmicroservice.models.Note;

/**
 * This interface defines all the business logic for the Note entity
 * 
 * @author Rémi Deronzier
 */
public interface NoteService {
    public List<Note> findByPatientId(long patientId);
}
