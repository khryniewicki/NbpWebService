package pl.com.khryniewicki.service;


import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.com.khryniewicki.response.*;

@Endpoint
@RequiredArgsConstructor
public class ResponseEndpoint {

    private static final String NAMESPACE_URI = "http://response.khryniewicki.com.pl";
    private final CurrencyRepository currencyRepository;
    private final ApiNbpService apiNbpService;
    private final Validate validate;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCurrencyRequest")
    @ResponsePayload
    public GetCurrencyResponse getExchangeRate(@RequestPayload GetCurrencyRequest request) {
        GetCurrencyResponse response = new GetCurrencyResponse();
        if (validate.validateRequest(request, response)) return response;

        ExchangeRatesSeries exchangeObject = apiNbpService.getXMLFromApi(request);
        response.setExchangeRatesSeries(exchangeObject);

        return response;
    }


}
