package industrialaccident.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

//<<< EDA / CQRS
@Entity
@Table(name = "MyClaim_table")
@Data
public class MyClaim {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Long accidentId;
    private String businessCode;
    private String employeeId;
    private String name;
    private String hospitalCode;
    private Float amount;
    private String status;
    private String comments;
    private Date date;
}
