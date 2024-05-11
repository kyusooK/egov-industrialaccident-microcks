package industrialaccident.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class RequestSickLeaveBenefitCommand {

    private String employeeId;
    private String businessCode;
    private Integer period;
    private Long id;
}
