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
// @RequestMapping(value="/compensations")
public class CompensationController {

    @Resource(name = "compensationService")
    private CompensationService compensationService;

    @GetMapping("/compensations")
    public List<Compensation> getAllCompensations() throws Exception {
        // Get all compensations via CompensationService
        return compensationService.getAllCompensations();
    }

    @GetMapping("/compensations/{id}")
    public Optional<Compensation> getCompensationById(@PathVariable Long id)
        throws Exception {
        // Get a compensation by ID via CompensationService
        return compensationService.getCompensationById(id);
    }

    @PostMapping("/compensations")
    public Compensation createCompensation(
        @RequestBody Compensation compensation
    ) throws Exception {
        // Create a new compensation via CompensationService
        return compensationService.createCompensation(compensation);
    }

    @PutMapping("/compensations/{id}")
    public Compensation updateCompensation(
        @PathVariable Long id,
        @RequestBody Compensation compensation
    ) throws Exception {
        // Update an existing compensation via CompensationService
        return compensationService.updateCompensation(compensation);
    }

    @DeleteMapping("/compensations/{id}")
    public void deleteCompensation(@PathVariable Long id) throws Exception {
        // Delete a compensation via CompensationService
        compensationService.deleteCompensation(id);
    }

    @RequestMapping(
        value = "/compensations/{id}/processcompensation",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Compensation processCompensation(
        @PathVariable(value = "id") Long id,
        @RequestBody ProcessCompensationCommand processCompensationCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        return compensationService.processCompensation(
            processCompensationCommand
        );
    }
}
