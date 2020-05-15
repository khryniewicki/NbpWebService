package pl.com.khryniewicki.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.khryniewicki.dto.request.ExchangeRatesEntity;
import pl.com.khryniewicki.dto.request.RateEntity;
import pl.com.khryniewicki.dto.request.RequestHolder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RateEntityService {

    private final RateEntityRepository rateEntityRepository;


    public void create(RateEntity rateEntity) {
        rateEntityRepository.save(rateEntity);
    }

    public List<RateEntity> findByExchangeRatesAndDates(RequestHolder requestHolder) {
        ExchangeRatesEntity exchangeRatesEntity = requestHolder.getExchangeRatesEntity();
        LocalDate startingDate = requestHolder.getStartingDate();
        LocalDate endingDate = requestHolder.getEndingDate();

        return rateEntityRepository.findAllByExchangeAndEffectiveDateBetween(exchangeRatesEntity, startingDate, endingDate);
    }


    public boolean isRateRequestsStored(RequestHolder requestHolder) {
        boolean isRateRequestsStored = false;

        ExchangeRatesEntity exchangeRatesEntity = requestHolder.getExchangeRatesEntity();
        LocalDate startingDate = requestHolder.getStartingDate();
        LocalDate endingDate = requestHolder.getEndingDate();

        Optional<RateEntity> RateRequestInStartingDay = rateEntityRepository.findByExchangeAndEffectiveDate(exchangeRatesEntity, startingDate);
        Optional<RateEntity> RateRequestInEndingDay = rateEntityRepository.findByExchangeAndEffectiveDate(exchangeRatesEntity, endingDate);

        if (RateRequestInStartingDay.isPresent() && RateRequestInEndingDay.isPresent()) {
            isRateRequestsStored = true;
        }
        return isRateRequestsStored;
    }
}
