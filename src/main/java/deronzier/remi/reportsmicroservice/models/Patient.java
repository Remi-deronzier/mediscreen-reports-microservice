package deronzier.remi.reportsmicroservice.models;

import java.time.LocalDate;

import lombok.Data;

/**
 * This class is the POJO class for the Patient entity
 * 
 * @author Rémi Deronzier
 */
@Data
public class Patient {
    private long id;
    private String lastName;
    private String firstName;
    private LocalDate dateOfBirth;
    private Sex sex;
    private String address;
    private String phoneNumber;
}
