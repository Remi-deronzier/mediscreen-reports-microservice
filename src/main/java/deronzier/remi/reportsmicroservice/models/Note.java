package deronzier.remi.reportsmicroservice.models;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * This class is the POJO class for the Note entity
 * 
 * @author Rémi Deronzier
 */
@Data
public class Note {
    private String id;
    private long patientId;
    private String content;
    private LocalDateTime createdAt;
}
