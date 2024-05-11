package industrialaccident.domain;

import industrialaccident.domain.*;
import industrialaccident.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
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

    public AverageSalaryApplied(SickLeave aggregate) {
        super(aggregate);
    }

    public AverageSalaryApplied() {
        super();
    }
}
//>>> DDD / Domain Event
