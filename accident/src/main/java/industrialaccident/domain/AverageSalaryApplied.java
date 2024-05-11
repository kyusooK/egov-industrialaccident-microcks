package industrialaccident.domain;

import industrialaccident.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class AverageSalaryApplied extends AbstractEvent {

    private Long id;
    private Long accessmentId;
    private Long accidentId;
    private String businessCode;
    private String employeeId;
    private Float averageSalary;
    private Integer period;
    private String status;
    private Date date;
}
