package industrialaccident.external;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Data
public class RequestSickLeaveBenefitCommand {

    private String employeeId;
    private String businessCode;
    private Integer period;

    @Id
    private Long id;
}
