package pl.com.khryniewicki.service.requestService;

import pl.com.khryniewicki.dto.request.ExchangeRatesRequest;
import pl.com.khryniewicki.dto.request.RateRequest;
import pl.com.khryniewicki.dto.request.RequestHolder;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

public interface RateRequestService {
    void create(RateRequest rateRequest);

    boolean isRateRequestsStored(RequestHolder requestHolder);

    List<RateRequest> findByExchangeRatesAndDates(RequestHolder requestHolder);

    Optional<RateRequest> getRateRequestByExchangeAndDate(ExchangeRatesRequest exchange, GregorianCalendar startingDate);
}
