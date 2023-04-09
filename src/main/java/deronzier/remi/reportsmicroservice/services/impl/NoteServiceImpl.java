package deronzier.remi.reportsmicroservice.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import deronzier.remi.reportsmicroservice.models.Note;
import deronzier.remi.reportsmicroservice.proxies.NotesMicroserviceProxy;
import deronzier.remi.reportsmicroservice.services.NoteService;

/**
 * This class defines all the business logic for the Note entity
 * 
 * @author Rémi Deronzier
 */
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NotesMicroserviceProxy proxy;

    /**
     * @param patientId
     * @param pageable
     * @return List<Note>
     */
    @Override
    public List<Note> findByPatientId(long patientId) {
        return proxy.findByPatientId(patientId).getContent();
    }

}
