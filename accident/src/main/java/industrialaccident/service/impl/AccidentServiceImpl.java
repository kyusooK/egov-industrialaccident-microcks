package industrialaccident.service.impl;

import industrialaccident.domain.Accident;
import industrialaccident.domain.AccidentRepository;
import industrialaccident.domain.ApplyMedicalBenefitCommand;
import industrialaccident.domain.ApplySickLeaveBenefitCommand;
import industrialaccident.service.AccidentService;
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

@Service("accidentService")
@Transactional
public class AccidentServiceImpl
    extends EgovAbstractServiceImpl
    implements AccidentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(
        AccidentServiceImpl.class
    );

    @Autowired
    AccidentRepository accidentRepository;

    @Override
    public List<Accident> getAllAccidents() throws Exception {
        // Get all accidents
        return StreamSupport
            .stream(accidentRepository.findAll().spliterator(), false)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Accident> getAccidentById(Long id) throws Exception {
        // Get a accident by ID
        return accidentRepository.findById(id);
    }

    @Override
    public Accident createAccident(Accident accident) throws Exception {
        // Create a new accident
        return accidentRepository.save(accident);
    }

    @Override
    public Accident updateAccident(Accident accident) throws Exception {
        // Update an existing accident via AccidentService
        if (accidentRepository.existsById(accident.getId())) {
            return accidentRepository.save(accident);
        } else {
            throw processException("info.nodata.msg");
        }
    }

    @Override
    public void deleteAccident(Long id) throws Exception {
        // Delete a accident
        accidentRepository.deleteById(id);
    }

    @Override
    public Accident applyMedicalBenefit(
        ApplyMedicalBenefitCommand applyMedicalBenefitCommand
    ) throws Exception {
        Accident accident = new Accident();

        accident.setBusinessCode(applyMedicalBenefitCommand.getBusinessCode());
        accident.setEmployeeId(applyMedicalBenefitCommand.getEmployeeId());
        accident.setHospitalCode(applyMedicalBenefitCommand.getHospitalCode());
        accident.setDoctorNote(applyMedicalBenefitCommand.getDoctorNote());
        accident.setAccidentType(applyMedicalBenefitCommand.getAccidentType());
        accident.setStatus("요양급여신청됨");

        accidentRepository.save(accident);

        return accident;
    }

    @Override
    public Accident applySickLeaveBenefit(
        ApplySickLeaveBenefitCommand applySickLeaveBenefitCommand
    ) throws Exception {

        Optional<Accident> optionalAccident = accidentRepository.findById(
            applySickLeaveBenefitCommand.getId()
        );

        if (optionalAccident.isPresent()) {
            Accident accident = optionalAccident.get();

            accident.setPeriod(applySickLeaveBenefitCommand.getPeriod());
            accident.setStatus("휴업급여 신칭됨");
            accident.applySickLeaveBenefit(applySickLeaveBenefitCommand);
            accidentRepository.save(accident);

            return accident;
        } else {
            throw processException("info.nodata.msg");
        }
    }
}
