package industrialaccident.domain;

import industrialaccident.domain.*;
import industrialaccident.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class CompensationPaid extends AbstractEvent {

    private Long id;
    private Long sickLeaveId;
    private Long assessmentId;
    private Long accidentId;
    private String employeeId;
    private Float amount;
    private String method;
    private Date date;
    private String status;

    public CompensationPaid(Compensation aggregate) {
        super(aggregate);
    }

    public CompensationPaid() {
        super();
    }
}
//>>> DDD / Domain Event
