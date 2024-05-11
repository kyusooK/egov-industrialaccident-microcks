package industrialaccident.infra;

import industrialaccident.config.kafka.KafkaProcessor;
import industrialaccident.domain.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class MyClaimViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private MyClaimRepository myClaimRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenMedicalBenefitApplied_then_CREATE_1(
        @Payload MedicalBenefitApplied medicalBenefitApplied
    ) {
        try {
            if (!medicalBenefitApplied.validate()) return;

            // view 객체 생성
            MyClaim myClaim = new MyClaim();
            // view 객체에 이벤트의 Value 를 set 함
            myClaim.setAccidentId(medicalBenefitApplied.getId());
            myClaim.setBusinessCode(medicalBenefitApplied.getBusinessCode());
            myClaim.setEmployeeId(medicalBenefitApplied.getEmployeeId());
            myClaim.setName(medicalBenefitApplied.getName());
            myClaim.setStatus(medicalBenefitApplied.getStatus());
            myClaim.setDate(medicalBenefitApplied.getApplyDt());
            myClaim.setHospitalCode(medicalBenefitApplied.getHospitalCode());
            // view 레파지 토리에 save
            myClaimRepository.save(myClaim);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenInvestigationApproved_then_UPDATE_1(
        @Payload InvestigationApproved investigationApproved
    ) {
        try {
            if (!investigationApproved.validate()) return;
            // view 객체 조회

            List<MyClaim> myClaimList = myClaimRepository.findByAccidentId(
                investigationApproved.getAccidentId()
            );
            for (MyClaim myClaim : myClaimList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                myClaim.setStatus(investigationApproved.getResults());
                myClaim.setComments(investigationApproved.getComments());
                myClaim.setDate(investigationApproved.getDate());
                // view 레파지 토리에 save
                myClaimRepository.save(myClaim);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenInvestigationDisapproved_then_UPDATE_2(
        @Payload InvestigationDisapproved investigationDisapproved
    ) {
        try {
            if (!investigationDisapproved.validate()) return;
            // view 객체 조회

            List<MyClaim> myClaimList = myClaimRepository.findByAccidentId(
                investigationDisapproved.getAccidentId()
            );
            for (MyClaim myClaim : myClaimList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                myClaim.setStatus(investigationDisapproved.getResults());
                myClaim.setComments(investigationDisapproved.getComments());
                myClaim.setDate(investigationDisapproved.getDate());
                // view 레파지 토리에 save
                myClaimRepository.save(myClaim);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenAverageSalaryApplied_then_UPDATE_3(
        @Payload AverageSalaryApplied averageSalaryApplied
    ) {
        try {
            if (!averageSalaryApplied.validate()) return;
            // view 객체 조회

            List<MyClaim> myClaimList = myClaimRepository.findByAccidentId(
                averageSalaryApplied.getAccidentId()
            );
            for (MyClaim myClaim : myClaimList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                myClaim.setStatus(averageSalaryApplied.getStatus());
                myClaim.setDate(averageSalaryApplied.getDate());
                // view 레파지 토리에 save
                myClaimRepository.save(myClaim);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCompensationPaid_then_UPDATE_4(
        @Payload CompensationPaid compensationPaid
    ) {
        try {
            if (!compensationPaid.validate()) return;
            // view 객체 조회

            List<MyClaim> myClaimList = myClaimRepository.findByAccidentId(
                compensationPaid.getAccidentId()
            );
            for (MyClaim myClaim : myClaimList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                myClaim.setAmount(compensationPaid.getAmount());
                myClaim.setStatus(compensationPaid.getStatus());
                myClaim.setDate(compensationPaid.getDate());
                // view 레파지 토리에 save
                myClaimRepository.save(myClaim);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
