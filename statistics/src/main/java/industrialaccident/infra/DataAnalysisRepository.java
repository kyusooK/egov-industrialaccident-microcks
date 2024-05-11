package industrialaccident.infra;

import industrialaccident.domain.*;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "dataAnalyses",
    path = "dataAnalyses"
)
public interface DataAnalysisRepository
    extends PagingAndSortingRepository<DataAnalysis, Long> {}
