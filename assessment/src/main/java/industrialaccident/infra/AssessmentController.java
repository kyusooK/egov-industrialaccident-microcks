package industrialaccident.infra;

import industrialaccident.domain.*;
import industrialaccident.service.*;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping(value="/assessments")
public class AssessmentController {

    @Resource(name = "assessmentService")
    private AssessmentService assessmentService;

    @GetMapping("/assessments")
    public List<Assessment> getAllAssessments() throws Exception {
        // Get all assessments via AssessmentService
        return assessmentService.getAllAssessments();
    }

    @GetMapping("/assessments/{id}")
    public Optional<Assessment> getAssessmentById(@PathVariable Long id)
        throws Exception {
        // Get a assessment by ID via AssessmentService
        return assessmentService.getAssessmentById(id);
    }

    @PostMapping("/assessments")
    public Assessment createAssessment(@RequestBody Assessment assessment)
        throws Exception {
        // Create a new assessment via AssessmentService
        return assessmentService.createAssessment(assessment);
    }

    @PutMapping("/assessments/{id}")
    public Assessment updateAssessment(
        @PathVariable Long id,
        @RequestBody Assessment assessment
    ) throws Exception {
        // Update an existing assessment via AssessmentService
        return assessmentService.updateAssessment(assessment);
    }

    @DeleteMapping("/assessments/{id}")
    public void deleteAssessment(@PathVariable Long id) throws Exception {
        // Delete a assessment via AssessmentService
        assessmentService.deleteAssessment(id);
    }

    @RequestMapping(
        value = "/assessments/{id}/update",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Assessment updateInvestigation(
        @PathVariable(value = "id") Long id,
        @RequestBody UpdateInvestigationCommand updateInvestigationCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        return assessmentService.updateInvestigation(
            updateInvestigationCommand
        );
    }
}
