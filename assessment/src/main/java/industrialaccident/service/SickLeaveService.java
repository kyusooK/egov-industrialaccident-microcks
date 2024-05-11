package industrialaccident.service;

import industrialaccident.domain.*;
import java.util.List;
import java.util.Optional;

public interface SickLeaveService {
    List<SickLeave> getAllSickLeaves() throws Exception;
    Optional<SickLeave> getSickLeaveById(Long id) throws Exception;
    SickLeave createSickLeave(SickLeave sickLeave) throws Exception;
    SickLeave updateSickLeave(SickLeave sickLeave) throws Exception;
    void deleteSickLeave(Long id) throws Exception;

    SickLeave requestSickLeaveBenefit(
        RequestSickLeaveBenefitCommand requestSickLeaveBenefitCommand
    ) throws Exception;
}
