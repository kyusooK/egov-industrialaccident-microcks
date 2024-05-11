package industrialaccident.domain;

import industrialaccident.domain.*;
import industrialaccident.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class InvestigationDisapproved extends AbstractEvent {

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
}
