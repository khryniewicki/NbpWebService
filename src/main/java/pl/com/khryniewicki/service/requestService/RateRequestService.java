package pl.com.khryniewicki.service.requestService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.khryniewicki.dto.repository.RateRequestRepository;
import pl.com.khryniewicki.dto.request.ExchangeRatesRequest;
import pl.com.khryniewicki.dto.request.RateRequest;
import pl.com.khryniewicki.dto.request.RequestHolder;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RateRequestService {

    private final RateRequestRepository rateRequestRepository;
    private final ExchangeRatesServiceImp exchangeRatesService;

    public void create(RateRequest rateRequest) {
        rateRequestRepository.save(rateRequest);
    }

    public boolean isRateRequestsStored(RequestHolder requestHolder) {
        GregorianCalendar startingDate = requestHolder.getStartingDate();
        GregorianCalendar endingDate = requestHolder.getEndingDate();

        ExchangeRatesRequest exchange = exchangeRatesService.findByCurrency(requestHolder.getCurrency());
        Optional<RateRequest> RateRequestInStartingDay = rateRequestRepository.findRateRequestByExchangeAndEffectiveDate(exchange, startingDate);
        Optional<RateRequest> RateRequestInEndingDay = rateRequestRepository.findRateRequestByExchangeAndEffectiveDate(exchange, endingDate);

        return RateRequestInStartingDay.isPresent() && RateRequestInEndingDay.isPresent();
    }


    public List<RateRequest> findByExchangeRatesAndDates(RequestHolder requestHolder) {
        ExchangeRatesRequest exchangeRatesRequest = exchangeRatesService.findByCurrency(requestHolder.getCurrency());
        GregorianCalendar startingDate = requestHolder.getStartingDate();
        GregorianCalendar endingDate = requestHolder.getEndingDate();

        return rateRequestRepository.findAllByExchangeAndEffectiveDateBetween(exchangeRatesRequest, startingDate, endingDate);
    }


}
