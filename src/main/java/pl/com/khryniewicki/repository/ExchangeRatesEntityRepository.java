package pl.com.khryniewicki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.khryniewicki.dto.request.ExchangeRatesEntity;

@Repository
public interface ExchangeRatesEntityRepository  extends JpaRepository<ExchangeRatesEntity,Long> {
}
