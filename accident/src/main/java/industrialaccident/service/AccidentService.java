package industrialaccident.service;

import industrialaccident.domain.*;
import java.util.List;
import java.util.Optional;

public interface AccidentService {
    List<Accident> getAllAccidents() throws Exception;
    Optional<Accident> getAccidentById(Long id) throws Exception;
    Accident createAccident(Accident accident) throws Exception;
    Accident updateAccident(Accident accident) throws Exception;
    void deleteAccident(Long id) throws Exception;

    Accident applyMedicalBenefit(
        ApplyMedicalBenefitCommand applyMedicalBenefitCommand
    ) throws Exception;
    Accident applySickLeaveBenefit(
        ApplySickLeaveBenefitCommand applySickLeaveBenefitCommand
    ) throws Exception;
}
