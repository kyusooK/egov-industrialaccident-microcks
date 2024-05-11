package industrialaccident.infra;

import industrialaccident.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class StatisticsHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Statistics>> {

    @Override
    public EntityModel<Statistics> process(EntityModel<Statistics> model) {
        return model;
    }
}
