package industrialaccident.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CreateInvestigationCommand {

    private Long accidentId;
    private String businessCode;
    private String employeeId;
    private String hospitalCode;
    private String doctorNote;
    private String accidentType;
}
