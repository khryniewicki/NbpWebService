package pl.com.khryniewicki.service.requestService;

import pl.com.khryniewicki.dto.request.ExchangeRatesRequest;

public interface ExchangeRatesService {
    void create(ExchangeRatesRequest exchangeRatesRequest);
    ExchangeRatesRequest findByCurrency(String currency);
}
