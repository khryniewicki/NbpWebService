package pl.com.khryniewicki.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.khryniewicki.dto.request.ExchangeRatesEntity;

@Service
@RequiredArgsConstructor
public class ExchangeRatesService {
    private final ExchangeRatesEntityRepository exchangeRatesEntityRepository;


    private void create(ExchangeRatesEntity exchangeRatesEntity) {
        exchangeRatesEntityRepository.save(exchangeRatesEntity);
    }
}
