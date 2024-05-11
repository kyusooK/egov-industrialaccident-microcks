package industrialaccident.external;

import java.util.Date;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "assessment", url = "${api.url.assessment}")
public interface SickLeaveService {
    @RequestMapping(
        method = RequestMethod.PUT,
        path = "/sickLeaves/{id}/requestsickleavebenefit"
    )
    public void requestSickLeaveBenefit(
        @PathVariable("id") Long id,
        @RequestBody RequestSickLeaveBenefitCommand requestSickLeaveBenefitCommand
    );
}
