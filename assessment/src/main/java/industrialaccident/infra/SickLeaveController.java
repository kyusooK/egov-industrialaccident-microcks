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
// @RequestMapping(value="/sickLeaves")
public class SickLeaveController {

    @Resource(name = "sickLeaveService")
    private SickLeaveService sickLeaveService;

    @GetMapping("/sickLeaves")
    public List<SickLeave> getAllSickLeaves() throws Exception {
        // Get all sickLeaves via SickLeaveService
        return sickLeaveService.getAllSickLeaves();
    }

    @GetMapping("/sickLeaves/{id}")
    public Optional<SickLeave> getSickLeaveById(@PathVariable Long id)
        throws Exception {
        // Get a sickLeave by ID via SickLeaveService
        return sickLeaveService.getSickLeaveById(id);
    }

    @PostMapping("/sickLeaves")
    public SickLeave createSickLeave(@RequestBody SickLeave sickLeave)
        throws Exception {
        // Create a new sickLeave via SickLeaveService
        return sickLeaveService.createSickLeave(sickLeave);
    }

    @PutMapping("/sickLeaves/{id}")
    public SickLeave updateSickLeave(
        @PathVariable Long id,
        @RequestBody SickLeave sickLeave
    ) throws Exception {
        // Update an existing sickLeave via SickLeaveService
        return sickLeaveService.updateSickLeave(sickLeave);
    }

    @DeleteMapping("/sickLeaves/{id}")
    public void deleteSickLeave(@PathVariable Long id) throws Exception {
        // Delete a sickLeave via SickLeaveService
        sickLeaveService.deleteSickLeave(id);
    }

    // @RequestMapping(
    //     value = "/assessments/{id}/update",
    //     method = RequestMethod.PUT,
    //     produces = "application/json;charset=UTF-8"
    // )
    // public Assessment updateInvestigation(
    //     @PathVariable(value = "id") Long id,
    //     @RequestBody UpdateInvestigationCommand updateInvestigationCommand,
    //     HttpServletRequest request,
    //     HttpServletResponse response
    // ) throws Exception {
    //     return assessmentService.updateInvestigation(
    //         updateInvestigationCommand
    //     );
    // }

    @RequestMapping(
        value = "/sickLeaves/{id}/requestsickleavebenefit",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public SickLeave requestSickLeaveBenefit(
        @PathVariable(value = "id") Long id,
        @RequestBody RequestSickLeaveBenefitCommand requestSickLeaveBenefitCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        return sickLeaveService.requestSickLeaveBenefit(
            requestSickLeaveBenefitCommand
        );
    }
}
