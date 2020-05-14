package pl.com.khryniewicki.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.khryniewicki.dto.request.ExchangeRatesRequest;

@Service
@RequiredArgsConstructor
public class ExchangeRequestService {

    private final ExchangeRepo exchangeRepo;

    public void create(ExchangeRatesRequest exchangeRatesRequest) {
        exchangeRepo.save(exchangeRatesRequest);
    }

}
