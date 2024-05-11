package industrialaccident.domain;

import industrialaccident.domain.*;
import industrialaccident.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class DataAggregated extends AbstractEvent {

    private Long id;
    private Long accidentId;
    private String hospitalCode;
    private String businessCode;
    private String results;
    private Date date;

    public DataAggregated(Statistics aggregate) {
        super(aggregate);
    }

    public DataAggregated() {
        super();
    }
}
//>>> DDD / Domain Event
