package pl.com.khryniewicki.service.requestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.khryniewicki.dto.repository.RateRequestRepository;
import pl.com.khryniewicki.dto.request.ExchangeRatesRequest;
import pl.com.khryniewicki.dto.request.RateRequest;
import pl.com.khryniewicki.dto.request.RequestHolder;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

@Service
public class RateRequestServiceImp implements RateRequestService {
    @Autowired
    private RateRequestRepository rateRequestRepository;
    @Autowired
    private ExchangeRatesService exchangeRatesService;

    @Override
    public void create(RateRequest rateRequest) {
        rateRequestRepository.save(rateRequest);
    }

    @Override
    public boolean isRateRequestsStored(RequestHolder requestHolder) {
        GregorianCalendar startingDate = requestHolder.getStartingDate();
        GregorianCalendar endingDate = requestHolder.getEndingDate();

        ExchangeRatesRequest exchange = exchangeRatesService.findByCurrency(requestHolder.getCurrency());
        Optional<RateRequest> RateRequestInStartingDay = getRateRequestByExchangeAndDate(exchange, startingDate);
        Optional<RateRequest> RateRequestInEndingDay = getRateRequestByExchangeAndDate(exchange, endingDate);

        return RateRequestInStartingDay.isPresent() && RateRequestInEndingDay.isPresent();
    }

    @Override
    public Optional<RateRequest> getRateRequestByExchangeAndDate(ExchangeRatesRequest exchange, GregorianCalendar startingDate) {
        return rateRequestRepository.findRateRequestByExchangeAndEffectiveDate(exchange, startingDate);
    }

    @Override
    public List<RateRequest> findByExchangeRatesAndDates(RequestHolder requestHolder) {
        ExchangeRatesRequest exchangeRatesRequest = exchangeRatesService.findByCurrency(requestHolder.getCurrency());
        GregorianCalendar startingDate = requestHolder.getStartingDate();
        GregorianCalendar endingDate = requestHolder.getEndingDate();

        return rateRequestRepository.findAllByExchangeAndEffectiveDateBetween(exchangeRatesRequest, startingDate, endingDate);
    }

    @Override
    public Optional<RateRequest> findByEffectiveDate(GregorianCalendar effectiveDate) {
        return rateRequestRepository.findByEffectiveDate(effectiveDate);
    }

    ;
}
