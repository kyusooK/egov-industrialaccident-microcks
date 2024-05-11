package industrialaccident.domain;

import industrialaccident.domain.*;
import industrialaccident.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class MedicalBenefitApplied extends AbstractEvent {

    private Long id;
    private String businessCode;
    private String employeeId;
    private String name;
    private String hospitalCode;
    private String doctorNote;
    private String accidentType;
    private String status;
    private Date applyDt;
}
