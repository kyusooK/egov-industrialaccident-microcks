package industrialaccident.domain;

import industrialaccident.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "assessments",
    path = "assessments"
)
public interface AssessmentRepository
    extends PagingAndSortingRepository<Assessment, Long> {}
