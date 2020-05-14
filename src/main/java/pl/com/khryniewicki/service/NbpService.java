package pl.com.khryniewicki.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.khryniewicki.request.ExchangeRatesRequest;
import pl.com.khryniewicki.response.*;

@Service
@RequiredArgsConstructor
public class NbpService {
    private final CurrencyService currencyService;
    private final ConvertExchangeRateService convertExchangeRateService;

    public ExchangeRatesSeries getXMLFromApi(GetCurrencyRequest request) {
        String currencyFullName = request.getCurrency();
        String startingDate = request.getStartingDate();
        String endingDate = request.getEndingDate();

        String fullXML = currencyService.parseXmlFromNBPApiToString(currencyFullName, startingDate, endingDate);
        ExchangeRatesRequest exchangeRequestObject = currencyService.parseStringToExchangeRateRequest(fullXML);

        System.out.println(exchangeRequestObject.toString());
        return convertExchangeRateService.convertExchangeRequestToExchangeResponse(exchangeRequestObject);
    }




}
