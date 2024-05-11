package industrialaccident.domain;

import industrialaccident.AccidentApplication;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Accident_table")
@Data
//<<< DDD / Aggregate Root
public class Accident {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String businessCode;

    private String employeeId;

    private String name;

    private String hospitalCode;

    private Integer period;

    private String doctorNote;

    private String accidentType;

    private String status;

    private Date applyDt;

    @PostPersist
    public void onPostPersist() {}

    public static AccidentRepository repository() {
        AccidentRepository accidentRepository = AccidentApplication.applicationContext.getBean(
            AccidentRepository.class
        );
        return accidentRepository;
    }

    public void apply() {
        //
    }

    //<<< Clean Arch / Port Method
    public void applyMedicalBenefit(
        ApplyMedicalBenefitCommand applyMedicalBenefitCommand
    ) {
        //implement business logic here:

        MedicalBenefitApplied medicalBenefitApplied = new MedicalBenefitApplied(
            this
        );
        medicalBenefitApplied.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void applySickLeaveBenefit(
        ApplySickLeaveBenefitCommand applySickLeaveBenefitCommand
    ) {
        //implement business logic here:

        SickLeaveBenefitApplied sickLeaveBenefitApplied = new SickLeaveBenefitApplied(
            this
        );
        sickLeaveBenefitApplied.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        industrialaccident.external.SickLeave sickLeave = new industrialaccident.external.SickLeave();
        // mappings goes here
        AccidentApplication.applicationContext
            .getBean(industrialaccident.external.SickLeaveService.class)
            .requestSickLeaveBenefit(sickLeave);
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
