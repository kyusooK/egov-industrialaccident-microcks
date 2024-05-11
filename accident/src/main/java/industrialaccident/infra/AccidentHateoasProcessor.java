package industrialaccident.infra;

import industrialaccident.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class AccidentHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Accident>> {

    @Override
    public EntityModel<Accident> process(EntityModel<Accident> model) {
        model.add(
            Link.of(model.getRequiredLink("self").getHref() + "/").withRel("")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/applysickleavepay"
                )
                .withRel("applysickleavepay")
        );

        return model;
    }
}
