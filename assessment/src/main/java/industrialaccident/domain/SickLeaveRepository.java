package industrialaccident.domain;

import industrialaccident.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "sickLeaves",
    path = "sickLeaves"
)
public interface SickLeaveRepository
    extends PagingAndSortingRepository<SickLeave, Long> {}
