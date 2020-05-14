package pl.com.khryniewicki.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.khryniewicki.dto.request.RateRequest;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RateRequestService {

    private final RateRequestRepository rateRequestRepository;

    public void create(RateRequest rates) {
        System.out.println(rates.toString());
        rateRequestRepository.save(rates);
    }



//    @PostConstruct
//    public void initData() {
//        ExchangeRatesRequest exchangeRatesSeries = new ExchangeRatesRequest();
//        exchangeRatesSeries.setCode(Code.USD);
//        exchangeRatesSeries.setCurrency("dolar amerykański");
//
//
//        RateRequest rateRequest = new RateRequest();
//        rateRequest.setAsk(4.32f);
//        rateRequest.setBid(4.12f);
//        rateRequest.setNo("sdsd332");
//        rateRequest.setEffectiveDate("12-03-12");
//        ArrayList<RateRequest> list = new ArrayList<>();
//        list.add(rateRequest);
//        exchangeRatesSeries.setRateRequests(list);
//        create(rateRequest);
//    }
}
