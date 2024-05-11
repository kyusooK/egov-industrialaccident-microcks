package industrialaccident.service;

import industrialaccident.domain.*;
import java.util.List;
import java.util.Optional;

public interface AssessmentService {
    List<Assessment> getAllAssessments() throws Exception;
    Optional<Assessment> getAssessmentById(Long id) throws Exception;
    Assessment createAssessment(Assessment assessment) throws Exception;
    Assessment updateAssessment(Assessment assessment) throws Exception;
    void deleteAssessment(Long id) throws Exception;

    Assessment updateInvestigation(
        UpdateInvestigationCommand updateInvestigationCommand
    ) throws Exception;
}
