package industrialaccident.domain;

import industrialaccident.domain.*;
import industrialaccident.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class InvestigationApproved extends AbstractEvent {

    private Long id;
    private Long accidentId;
    private String businessCode;
    private String employeeId;
    private Long assessorId;
    private String hospitalCode;
    private String doctorNote;
    private String results;
    private Date date;
    private String comments;

    public InvestigationApproved(Assessment aggregate) {
        super(aggregate);
    }

    public InvestigationApproved() {
        super();
    }
}
//>>> DDD / Domain Event
