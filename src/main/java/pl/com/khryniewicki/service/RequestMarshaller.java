package pl.com.khryniewicki.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.khryniewicki.dto.request.ExchangeRatesRequest;
import pl.com.khryniewicki.dto.request.RateRequest;
import pl.com.khryniewicki.dto.request.RequestHolder;
import pl.com.khryniewicki.repository.RateEntityService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestMarshaller {

    private final RateEntityService rateEntityService;

    protected ExchangeRatesRequest exchangeRatesMarshaller(RequestHolder requestHolder) {
        List<RateRequest> listWithRateEntity = rateEntityService.findByExchangeRatesAndDates(requestHolder);
        System.out.println("db find2A");
        ExchangeRatesRequest exchangeRates = listWithRateEntity.get(0).getExchange();
        exchangeRates.setRateRequests(listWithRateEntity);
        return exchangeRates;
    }

}
