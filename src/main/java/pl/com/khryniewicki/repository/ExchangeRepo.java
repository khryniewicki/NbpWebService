package pl.com.khryniewicki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.khryniewicki.dto.request.ExchangeRatesRequest;

@Repository
public interface ExchangeRepo extends JpaRepository<ExchangeRatesRequest,Long> {
}
