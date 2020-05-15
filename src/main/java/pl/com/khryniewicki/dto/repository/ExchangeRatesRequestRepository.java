package pl.com.khryniewicki.dto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.khryniewicki.dto.request.ExchangeRatesRequest;

@Repository
public interface ExchangeRatesRequestRepository extends JpaRepository<ExchangeRatesRequest,Long> {
    ExchangeRatesRequest findExchangeRatesRequestByCurrency(String currency);
}
