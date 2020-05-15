package pl.com.khryniewicki.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.khryniewicki.dto.request.ExchangeRatesRequest;

@Service
@RequiredArgsConstructor
public class ExchangeRatesService {
    private final ExchangeRatesRequestRepository exchangeRatesRequestRepository;


    public void create(ExchangeRatesRequest exchangeRatesRequest) {
        exchangeRatesRequestRepository.save(exchangeRatesRequest);
    }

    public ExchangeRatesRequest findByCurrency(String currency) {
        return exchangeRatesRequestRepository.findExchangeRatesRequestByCurrency(currency);
    }



}
