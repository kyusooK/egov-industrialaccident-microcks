package industrialaccident.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

//<<< EDA / CQRS
@Entity
@Table(name = "DataAnalysis_table")
@Data
public class DataAnalysis {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String accidentId;
    private String businessCode;
    private String hospitalCode;
    private String results;
    private Date date;
}
