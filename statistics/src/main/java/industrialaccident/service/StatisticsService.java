package industrialaccident.service;

import industrialaccident.domain.*;
import java.util.List;
import java.util.Optional;

public interface StatisticsService {
    List<Statistics> getAllStatistics() throws Exception;
    Optional<Statistics> getStatisticsById(Long id) throws Exception;
    Statistics createStatistics(Statistics statistics) throws Exception;
    Statistics updateStatistics(Statistics statistics) throws Exception;
    void deleteStatistics(Long id) throws Exception;
}
