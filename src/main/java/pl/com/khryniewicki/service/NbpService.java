package pl.com.khryniewicki.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.khryniewicki.dto.response.*;


@Service
@RequiredArgsConstructor

public class NbpService {

    private final ConvertExchangeRateService convertExchangeRateService;

    public ExchangeRatesSeries getXMLFromApi(GetCurrencyRequest request) {
        String currencyFullName = request.getCurrency();
        String startingDate = request.getStartingDate();
        String endingDate = request.getEndingDate();
        return convertExchangeRateService.getExchangeRatesSeries(currencyFullName, startingDate, endingDate);
    }


}
