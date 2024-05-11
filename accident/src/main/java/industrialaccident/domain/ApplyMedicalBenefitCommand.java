package industrialaccident.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class ApplyMedicalBenefitCommand {

    private String businessCode;
    private String employeeId;
    private String name;
    private String hospitalCode;
    private String doctorNote;
    private String accidentType;
}
