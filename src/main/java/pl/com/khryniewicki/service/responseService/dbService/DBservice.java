package pl.com.khryniewicki.service.responseService.dbService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.khryniewicki.dto.request.ExchangeRatesRequest;
import pl.com.khryniewicki.dto.request.RateRequest;
import pl.com.khryniewicki.dto.request.RequestHolder;
import pl.com.khryniewicki.service.requestService.ExchangeRatesServiceImp;
import pl.com.khryniewicki.service.requestService.RateRequestService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DBservice {

    private final ExchangeRatesServiceImp exchangeRatesService;
    private final RateRequestService rateRequestService;

    public void saveExchangeRates(ExchangeRatesRequest unmarshal) {
        List<RateRequest> rateRequests = unmarshal.getRateRequests();
        exchangeRatesService.create(unmarshal);
        ExchangeRatesRequest byCurrency = exchangeRatesService.findByCurrency(unmarshal.getCurrency());
        rateRequests.forEach(rate -> rate.setExchange(byCurrency));
        rateRequests.forEach(rateRequestService::create);
    }


    public ExchangeRatesRequest findExchangeRates(RequestHolder requestHolder) {
        List<RateRequest> listWithRateEntity = rateRequestService.findByExchangeRatesAndDates(requestHolder);
        ExchangeRatesRequest exchangeRates = exchangeRatesService.findByCurrency(requestHolder.getCurrency());
        exchangeRates.setRateRequests(listWithRateEntity);
        return exchangeRates;
    }
}
