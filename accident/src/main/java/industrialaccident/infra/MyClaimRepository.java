package industrialaccident.infra;

import industrialaccident.domain.*;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "myClaims", path = "myClaims")
public interface MyClaimRepository
    extends PagingAndSortingRepository<MyClaim, Long> {
    List<MyClaim> findByAccidentId(Long accidentId);
}
