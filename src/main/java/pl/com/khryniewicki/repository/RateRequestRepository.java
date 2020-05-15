package pl.com.khryniewicki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.khryniewicki.dto.request.ExchangeRatesRequest;
import pl.com.khryniewicki.dto.request.RateRequest;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

@Repository
public interface RateRequestRepository extends JpaRepository<RateRequest,Long> {

    List<RateRequest> findAllByExchangeAndEffectiveDateBetween(ExchangeRatesRequest exchangeRatesRequest, GregorianCalendar statingDate, GregorianCalendar endingDate);
    Optional<RateRequest> findRateRequestByExchangeAndEffectiveDate(ExchangeRatesRequest exchangeRatesRequest, GregorianCalendar date);
}
