package pl.com.khryniewicki.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.khryniewicki.dto.request.*;
import pl.com.khryniewicki.repository.RateEntityService;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class RequestMarshaller {

    private final RateEntityService rateEntityService;

    protected ExchangeRatesRequest exchangeRatesMarshaller(RequestHolder requestHolder) {
        List<RateEntity> listWithRateEntity = rateEntityService.findByExchangeRatesAndDates(requestHolder);

        List<RateRequest> listWithRateRequests = listWithRateEntity.stream()
                .map(rate -> RateEntityAdapter(rate))
                .collect(Collectors.toList());

        ExchangeRatesEntity exchangeRates = listWithRateEntity.get(0).getExchange();

        return ExchangeRatesAdapter(exchangeRates, listWithRateRequests);
    }

    private ExchangeRatesRequest ExchangeRatesAdapter(ExchangeRatesEntity exchangeRates, List<RateRequest> listWithRateRequests) {

        ExchangeRatesRequest exchangeRatesRequest = new ExchangeRatesRequest();
        exchangeRatesRequest.setCurrency(exchangeRates.getCurrency());
        exchangeRatesRequest.setCode(exchangeRates.getCode());
//        exchangeRatesRequest.setTable(exchangeRates.getTable());
        exchangeRatesRequest.setRateRequests(listWithRateRequests);

        return exchangeRatesRequest;
    }

    private RateRequest RateEntityAdapter(RateEntity rate) {
        RateRequest rateRequest = new RateRequest();

        rateRequest.setNo(rate.getNo());
        rateRequest.setBid(rate.getBid());
        rateRequest.setAsk(rate.getAsk());
        rateRequest.setEffectiveDate(rate.getEffectiveDate().toString());

        return rateRequest;
    }

}
