package industrialaccident.service.impl;

import industrialaccident.domain.Assessment;
import industrialaccident.domain.AssessmentRepository;
import industrialaccident.domain.CreateInvestigationCommand;
import industrialaccident.domain.UpdateInvestigationCommand;
import industrialaccident.service.AssessmentService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("assessmentService")
@Transactional
public class AssessmentServiceImpl
    extends EgovAbstractServiceImpl
    implements AssessmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(
        AssessmentServiceImpl.class
    );

    @Autowired
    AssessmentRepository assessmentRepository;

    @Override
    public List<Assessment> getAllAssessments() throws Exception {
        // Get all assessments
        return StreamSupport
            .stream(assessmentRepository.findAll().spliterator(), false)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Assessment> getAssessmentById(Long id) throws Exception {
        // Get a assessment by ID
        return assessmentRepository.findById(id);
    }

    @Override
    public Assessment createAssessment(Assessment assessment) throws Exception {
        // Create a new assessment
        return assessmentRepository.save(assessment);
    }

    @Override
    public Assessment updateAssessment(Assessment assessment) throws Exception {
        // Update an existing assessment via AssessmentService
        if (assessmentRepository.existsById(assessment.getId())) {
            return assessmentRepository.save(assessment);
        } else {
            throw processException("info.nodata.msg");
        }
    }

    @Override
    public void deleteAssessment(Long id) throws Exception {
        // Delete a assessment
        assessmentRepository.deleteById(id);
    }

    @Override
    public Assessment updateInvestigation(
        UpdateInvestigationCommand updateInvestigationCommand
    ) throws Exception {
        // You can implement logic here, or call the domain method of the Assessment.

        /** Option 1-1:  implement logic here     
            Assessment assessment = new Assessment();
            assessment.setUserId(event.getUserId());

            assessmentRepository.save(assessment);   
        */

        // Optional<Assessment> optionalAssessment = assessmentRepository.findById(
        //     updateInvestigationCommand.getAssessmentId()
        // );

        // if (optionalAssessment.isPresent()) {
        //     Assessment assessment = optionalAssessment.get();

        //     // business Logic....
        //     assessment.updateInvestigation(updateInvestigationCommand);
        //     assessmentRepository.save(assessment);

        //     return assessment;
        // } else {
        //     throw processException("info.nodata.msg");
        // }
        return null;
    }
}
