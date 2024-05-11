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
// @RequestMapping(value="/statistics")
public class StatisticsController {

    @Resource(name = "statisticsService")
    private StatisticsService statisticsService;

    @GetMapping("/statistics")
    public List<Statistics> getAllStatistics() throws Exception {
        // Get all statistics via StatisticsService
        return statisticsService.getAllStatistics();
    }

    @GetMapping("/statistics/{id}")
    public Optional<Statistics> getStatisticsById(@PathVariable Long id)
        throws Exception {
        // Get a statistics by ID via StatisticsService
        return statisticsService.getStatisticsById(id);
    }

    @PostMapping("/statistics")
    public Statistics createStatistics(@RequestBody Statistics statistics)
        throws Exception {
        // Create a new statistics via StatisticsService
        return statisticsService.createStatistics(statistics);
    }

    @PutMapping("/statistics/{id}")
    public Statistics updateStatistics(
        @PathVariable Long id,
        @RequestBody Statistics statistics
    ) throws Exception {
        // Update an existing statistics via StatisticsService
        return statisticsService.updateStatistics(statistics);
    }

    @DeleteMapping("/statistics/{id}")
    public void deleteStatistics(@PathVariable Long id) throws Exception {
        // Delete a statistics via StatisticsService
        statisticsService.deleteStatistics(id);
    }
}
