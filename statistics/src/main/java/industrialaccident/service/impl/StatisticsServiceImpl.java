package industrialaccident.service.impl;

import industrialaccident.domain.Statistics;
import industrialaccident.domain.StatisticsRepository;
import industrialaccident.service.StatisticsService;
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

@Service("statisticsService")
@Transactional
public class StatisticsServiceImpl
    extends EgovAbstractServiceImpl
    implements StatisticsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(
        StatisticsServiceImpl.class
    );

    @Autowired
    StatisticsRepository statisticsRepository;

    @Override
    public List<Statistics> getAllStatistics() throws Exception {
        // Get all statistics
        return StreamSupport
            .stream(statisticsRepository.findAll().spliterator(), false)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Statistics> getStatisticsById(Long id) throws Exception {
        // Get a statistics by ID
        return statisticsRepository.findById(id);
    }

    @Override
    public Statistics createStatistics(Statistics statistics) throws Exception {
        // Create a new statistics
        return statisticsRepository.save(statistics);
    }

    @Override
    public Statistics updateStatistics(Statistics statistics) throws Exception {
        // Update an existing statistics via StatisticsService
        if (statisticsRepository.existsById(statistics.getId())) {
            return statisticsRepository.save(statistics);
        } else {
            throw processException("info.nodata.msg");
        }
    }

    @Override
    public void deleteStatistics(Long id) throws Exception {
        // Delete a statistics
        statisticsRepository.deleteById(id);
    }
}
