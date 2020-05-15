package pl.com.khryniewicki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.khryniewicki.dto.request.ExchangeRatesEntity;
import pl.com.khryniewicki.dto.request.RateEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RateEntityRepository extends JpaRepository<RateEntity,Long> {
    List<RateEntity> findAllByExchangeAndEffectiveDateBetween(ExchangeRatesEntity exchange, LocalDate statingDate,LocalDate endingDate);
    Optional<RateEntity> findByExchangeAndEffectiveDate(ExchangeRatesEntity exchange,LocalDate date);
}
