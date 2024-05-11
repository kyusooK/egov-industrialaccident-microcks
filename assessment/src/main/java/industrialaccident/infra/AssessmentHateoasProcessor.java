package industrialaccident.infra;

import industrialaccident.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class AssessmentHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Assessment>> {

    @Override
    public EntityModel<Assessment> process(EntityModel<Assessment> model) {
        model.add(
            Link.of(model.getRequiredLink("self").getHref() + "/").withRel("")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/update")
                .withRel("update")
        );

        return model;
    }
}
