package industrialaccident.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CreateSickLeaveBenefitCommand {

    private Long accessmentId;
    private Long accidentId;
    private String businessCode;
    private String employeeId;
}
