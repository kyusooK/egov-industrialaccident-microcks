package industrialaccident.domain;

import industrialaccident.StatisticsApplication;
import industrialaccident.domain.DataAggregated;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Statistics_table")
@Data
//<<< DDD / Aggregate Root
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long accidentId;

    private String hospitalCode;

    private String businessCode;

    private String results;

    private Date date;

    @PostPersist
    public void onPostPersist() {
        DataAggregated dataAggregated = new DataAggregated(this);
        dataAggregated.publishAfterCommit();
    }

    public static StatisticsRepository repository() {
        StatisticsRepository statisticsRepository = StatisticsApplication.applicationContext.getBean(
            StatisticsRepository.class
        );
        return statisticsRepository;
    }

    //<<< Clean Arch / Port Method
    public static void aggregateData(
        InvestigationDisapproved investigationDisapproved
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        Statistics statistics = new Statistics();
        repository().save(statistics);

        DataAggregated dataAggregated = new DataAggregated(statistics);
        dataAggregated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(investigationDisapproved.get???()).ifPresent(statistics->{
            
            statistics // do something
            repository().save(statistics);

            DataAggregated dataAggregated = new DataAggregated(statistics);
            dataAggregated.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void aggregateData(
        InvestigationApproved investigationApproved
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        Statistics statistics = new Statistics();
        repository().save(statistics);

        DataAggregated dataAggregated = new DataAggregated(statistics);
        dataAggregated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(investigationApproved.get???()).ifPresent(statistics->{
            
            statistics // do something
            repository().save(statistics);

            DataAggregated dataAggregated = new DataAggregated(statistics);
            dataAggregated.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
