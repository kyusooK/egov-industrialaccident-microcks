package industrialaccident.service;

import industrialaccident.domain.*;
import java.util.List;
import java.util.Optional;

public interface CompensationService {
    List<Compensation> getAllCompensations() throws Exception;
    Optional<Compensation> getCompensationById(Long id) throws Exception;
    Compensation createCompensation(Compensation compensation) throws Exception;
    Compensation updateCompensation(Compensation compensation) throws Exception;
    void deleteCompensation(Long id) throws Exception;

    Compensation processCompensation(
        ProcessCompensationCommand processCompensationCommand
    ) throws Exception;
}
