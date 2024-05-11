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
}
