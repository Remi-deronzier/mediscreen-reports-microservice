package deronzier.remi.reportsmicroservice.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @param keywords
     * @return long
     */
    @Override
    public long countByPatientIdWithContentContainingTriggeringTerms(long patientId, List<String> keywords) {
        return proxy.countByPatientIdWithContentContainingTriggeringTerms(patientId, keywords);
    }

}
