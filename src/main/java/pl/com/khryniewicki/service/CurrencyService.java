package pl.com.khryniewicki.service;

import org.springframework.stereotype.Component;
import pl.com.khryniewicki.dto.request.*;
@Component
public interface CurrencyService {
    ExchangeRatesRequest parseStringToExchangeRateRequest(String fulltext);

    String parseXmlFromNBPApiToString(String currencyFullName, String startingDate, String endingDate);

    CodeRequest getCurrencyCodeUsingCurrencyName(String currencyFullName);
}
