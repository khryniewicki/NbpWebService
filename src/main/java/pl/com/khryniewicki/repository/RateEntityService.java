package pl.com.khryniewicki.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.khryniewicki.dto.request.ExchangeRatesRequest;
import pl.com.khryniewicki.dto.request.RateRequest;
import pl.com.khryniewicki.dto.request.RequestHolder;

import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RateEntityService {

    private final RateRequestRepository rateRequestRepository;
    private final ExchangeRatesService exchangeRatesService;

    public boolean isRateRequestsStored(RequestHolder requestHolder) {
        boolean isRateRequestsStored = false;
        GregorianCalendar startingDate = requestHolder.getStartingDate();
        GregorianCalendar endingDate = requestHolder.getEndingDate();

        ExchangeRatesRequest exchange = exchangeRatesService.findByCurrency(requestHolder.getCurrency());

        Optional<RateRequest> RateRequestInStartingDay = rateRequestRepository.findRateRequestByExchangeAndEffectiveDate(exchange, startingDate);
        Optional<RateRequest> RateRequestInEndingDay = rateRequestRepository.findRateRequestByExchangeAndEffectiveDate(exchange, endingDate);

        if (RateRequestInStartingDay.isPresent() && RateRequestInEndingDay.isPresent()) {
            isRateRequestsStored = true;
        }
        return isRateRequestsStored;
    }

    public void create(RateRequest rateRequest) {
        rateRequestRepository.save(rateRequest);
    }

    public List<RateRequest> findByExchangeRatesAndDates(RequestHolder requestHolder) {
        ExchangeRatesRequest exchangeRatesRequest = exchangeRatesService.findByCurrency(requestHolder.getCurrency());
        GregorianCalendar startingDate = requestHolder.getStartingDate();
        GregorianCalendar endingDate = requestHolder.getEndingDate();

        return rateRequestRepository.findAllByExchangeAndEffectiveDateBetween(exchangeRatesRequest, startingDate, endingDate);
    }



}
