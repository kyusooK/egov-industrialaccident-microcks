package industrialaccident.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class ApplySickLeaveBenefitCommand {

    private String businessCode;
    private String employeeId;
    private Integer period;
}
