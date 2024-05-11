package industrialaccident.domain;

import industrialaccident.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "notifications",
    path = "notifications"
)
public interface NotificationRepository
    extends PagingAndSortingRepository<Notification, Long> {}
