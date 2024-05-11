package industrialaccident.service.impl;

import industrialaccident.domain.Compensation;
import industrialaccident.domain.CompensationRepository;
import industrialaccident.domain.CreateCompensationCommand;
import industrialaccident.domain.ProcessCompensationCommand;
import industrialaccident.service.CompensationService;
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

@Service("compensationService")
@Transactional
public class CompensationServiceImpl
    extends EgovAbstractServiceImpl
    implements CompensationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(
        CompensationServiceImpl.class
    );

    @Autowired
    CompensationRepository compensationRepository;

    @Override
    public List<Compensation> getAllCompensations() throws Exception {
        // Get all compensations
        return StreamSupport
            .stream(compensationRepository.findAll().spliterator(), false)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Compensation> getCompensationById(Long id)
        throws Exception {
        // Get a compensation by ID
        return compensationRepository.findById(id);
    }

    @Override
    public Compensation createCompensation(Compensation compensation)
        throws Exception {
        // Create a new compensation
        return compensationRepository.save(compensation);
    }

    @Override
    public Compensation updateCompensation(Compensation compensation)
        throws Exception {
        // Update an existing compensation via CompensationService
        if (compensationRepository.existsById(compensation.getId())) {
            return compensationRepository.save(compensation);
        } else {
            throw processException("info.nodata.msg");
        }
    }

    @Override
    public void deleteCompensation(Long id) throws Exception {
        // Delete a compensation
        compensationRepository.deleteById(id);
    }

    @Override
    public Compensation processCompensation(
        ProcessCompensationCommand processCompensationCommand
    ) throws Exception {
        // You can implement logic here, or call the domain method of the Compensation.

        /** Option 1-1:  implement logic here     
            Compensation compensation = new Compensation();
            compensation.setUserId(event.getUserId());

            compensationRepository.save(compensation);   
        */

        // Optional<Compensation> optionalCompensation = compensationRepository.findById(
        //     processCompensationCommand.getCompensationId()
        // );

        // if (optionalCompensation.isPresent()) {
        //     Compensation compensation = optionalCompensation.get();

        //     // business Logic....
        //     compensation.processCompensation(processCompensationCommand);
        //     compensationRepository.save(compensation);

        //     return compensation;
        // } else {
        //     throw processException("info.nodata.msg");
        // }
        return null;
    }
}
