package industrialaccident.external;

import java.util.Date;
import lombok.Data;

@Data
public class SickLeave {

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
