package pl.com.khryniewicki.service.requestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.khryniewicki.dto.repository.ExchangeRatesRequestRepository;
import pl.com.khryniewicki.dto.request.ExchangeRatesRequest;

@Service
public class ExchangeRatesServiceImp implements ExchangeRatesService {
    @Autowired
    private ExchangeRatesRequestRepository exchangeRatesRequestRepository;

    @Override
    public void create(ExchangeRatesRequest exchangeRatesRequest) {
        String currency = exchangeRatesRequest.getCurrency();
        if (findByCurrency(currency) == null)
            exchangeRatesRequestRepository.save(exchangeRatesRequest);
    }

    @Override
    public ExchangeRatesRequest findByCurrency(String currency) {
        return exchangeRatesRequestRepository.findExchangeRatesRequestByCurrency(currency);
    }


}
