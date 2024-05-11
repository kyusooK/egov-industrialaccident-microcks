package industrialaccident.domain;

import industrialaccident.domain.*;
import industrialaccident.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class SickLeaveBenefitCreated extends AbstractEvent {

    private Long id;
    private Long accessmentId;
    private Long accidentId;
    private String businessCode;
    private String employeeId;
    private Float averageSalary;
    private Integer period;
    private String status;
    private Date date;

    public SickLeaveBenefitCreated(SickLeave aggregate) {
        super(aggregate);
    }

    public SickLeaveBenefitCreated() {
        super();
    }
}
//>>> DDD / Domain Event
