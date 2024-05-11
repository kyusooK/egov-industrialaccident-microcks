package industrialaccident.domain;

import industrialaccident.AssessmentApplication;
import industrialaccident.domain.InvestigationDisapproved;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Assessment_table")
@Data
//<<< DDD / Aggregate Root
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @PostPersist
    public void onPostPersist() {
        InvestigationDisapproved investigationDisapproved = new InvestigationDisapproved(
            this
        );
        investigationDisapproved.publishAfterCommit();
    }

    public static AssessmentRepository repository() {
        AssessmentRepository assessmentRepository = AssessmentApplication.applicationContext.getBean(
            AssessmentRepository.class
        );
        return assessmentRepository;
    }

    //<<< Clean Arch / Port Method
    public void createInvestigation(
        CreateInvestigationCommand createInvestigationCommand
    ) {
        //implement business logic here:

        InvestigationCreated investigationCreated = new InvestigationCreated(
            this
        );
        investigationCreated.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void updateInvestigation(
        UpdateInvestigationCommand updateInvestigationCommand
    ) {
        //implement business logic here:

        InvestigationApproved investigationApproved = new InvestigationApproved(
            this
        );
        investigationApproved.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
