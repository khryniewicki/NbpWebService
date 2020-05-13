package pl.com.khryniewicki.service;


import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.com.khryniewicki.request.ExchangeRatesRequest;
import pl.com.khryniewicki.response.ExchangeRatesSeries;
import pl.com.khryniewicki.response.GetCurrencyRequest;
import pl.com.khryniewicki.response.GetCurrencyResponse;

@Endpoint
@RequiredArgsConstructor
public class ResponseEndpoint {

    private static final String NAMESPACE_URI = "http://response.khryniewicki.com.pl";

    private final CurrencyRepository currencyRepository;
    private final ApiNbpService apiNbpService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCurrencyRequest")
    @ResponsePayload
    public pl.com.khryniewicki.response.GetCurrencyResponse getExchangeRate(@RequestPayload GetCurrencyRequest request) {
        GetCurrencyResponse getCurrencyResponse = new GetCurrencyResponse();
        ExchangeRatesSeries exchangeObject = apiNbpService.getXMLFromApi(request);
        System.out.println(exchangeObject);
//        getCurrencyResponse.setExchangeRatesSeries (currencyRepository.findCountry(request.getCurrency()));
        getCurrencyResponse.setExchangeRatesSeries (exchangeObject);

        return getCurrencyResponse;
    }
}
