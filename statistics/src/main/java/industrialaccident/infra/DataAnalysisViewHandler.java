package industrialaccident.infra;

import industrialaccident.config.kafka.KafkaProcessor;
import industrialaccident.domain.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class DataAnalysisViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private DataAnalysisRepository dataAnalysisRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenDataAggregated_then_CREATE_1(
        @Payload DataAggregated dataAggregated
    ) {
        try {
            if (!dataAggregated.validate()) return;

            // view 객체 생성
            DataAnalysis dataAnalysis = new DataAnalysis();
            // view 객체에 이벤트의 Value 를 set 함
            dataAnalysis.setAccidentId(
                String.valueOf(dataAggregated.getAccidentId())
            );
            dataAnalysis.setBusinessCode(dataAggregated.getBusinessCode());
            dataAnalysis.setResults(dataAggregated.getResults());
            dataAnalysis.setDate(dataAggregated.getDate());
            dataAnalysis.setHospitalCode(dataAggregated.getHospitalCode());
            // view 레파지 토리에 save
            dataAnalysisRepository.save(dataAnalysis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
