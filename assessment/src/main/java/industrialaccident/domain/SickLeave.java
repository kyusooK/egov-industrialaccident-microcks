package industrialaccident.domain;

import industrialaccident.AssessmentApplication;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "SickLeave_table")
@Data
//<<< DDD / Aggregate Root
public class SickLeave {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long accessmentId;

    private Long accidentId;

    private String businessCode;

    private String employeeId;

    private Float averageSalary;

    private Integer period;

    private String status;

    private Date date;

    @PostPersist
    public void onPostPersist() {}

    public static SickLeaveRepository repository() {
        SickLeaveRepository sickLeaveRepository = AssessmentApplication.applicationContext.getBean(
            SickLeaveRepository.class
        );
        return sickLeaveRepository;
    }

    //<<< Clean Arch / Port Method
    public void applySalary(ApplySalaryCommand applySalaryCommand) {
        //implement business logic here:

        AverageSalaryApplied averageSalaryApplied = new AverageSalaryApplied(
            this
        );
        averageSalaryApplied.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void createSickLeaveBenefit(
        CreateSickLeaveBenefitCommand createSickLeaveBenefitCommand
    ) {
        //implement business logic here:

        SickLeaveBenefitCreated sickLeaveBenefitCreated = new SickLeaveBenefitCreated(
            this
        );
        sickLeaveBenefitCreated.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void requestSickLeaveBenefit(
        RequestSickLeaveBenefitCommand requestSickLeaveBenefitCommand
    ) {
        //implement business logic here:

        SickLeaveBenefitRequested sickLeaveBenefitRequested = new SickLeaveBenefitRequested(
            this
        );
        sickLeaveBenefitRequested.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
