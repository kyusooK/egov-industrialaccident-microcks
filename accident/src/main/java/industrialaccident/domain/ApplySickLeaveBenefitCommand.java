package industrialaccident.domain;

import java.time.LocalDate;
import java.util.*;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class ApplySickLeaveBenefitCommand {

    @Id
    private Long id;
    private String businessCode;
    private String employeeId;
    private Integer period;
}
