package industrialaccident.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import industrialaccident.config.kafka.KafkaProcessor;
import industrialaccident.domain.*;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    StatisticsRepository statisticsRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='InvestigationDisapproved'"
    )
    public void wheneverInvestigationDisapproved_AggregateData(
        @Payload InvestigationDisapproved investigationDisapproved
    ) {
        InvestigationDisapproved event = investigationDisapproved;
        System.out.println(
            "\n\n##### listener AggregateData : " +
            investigationDisapproved +
            "\n\n"
        );

        Statistics.aggregateData(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='InvestigationApproved'"
    )
    public void wheneverInvestigationApproved_AggregateData(
        @Payload InvestigationApproved investigationApproved
    ) {
        InvestigationApproved event = investigationApproved;
        System.out.println(
            "\n\n##### listener AggregateData : " +
            investigationApproved +
            "\n\n"
        );

        Statistics.aggregateData(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
