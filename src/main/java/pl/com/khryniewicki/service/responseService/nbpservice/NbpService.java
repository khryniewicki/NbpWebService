package pl.com.khryniewicki.service.responseService.nbpservice;

import org.springframework.stereotype.Component;
import pl.com.khryniewicki.dto.request.CodeRequest;
import pl.com.khryniewicki.dto.request.ExchangeRatesRequest;

@Component
public interface NbpService {
    ExchangeRatesRequest unmarshallStringFromApi(String fulltext);
    String parseXmlFromNBPApiToString(String currencyFullName, String startingDate, String endingDate);
    CodeRequest getCurrencyCodeUsingCurrencyName(String currencyFullName);
}
