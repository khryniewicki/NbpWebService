package pl.com.khryniewicki.service;


import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.com.khryniewicki.dto.response.ExchangeRatesSeries;
import pl.com.khryniewicki.dto.response.GetCurrencyRequest;
import pl.com.khryniewicki.dto.response.GetCurrencyResponse;

import java.util.Objects;


@Endpoint
@RequiredArgsConstructor
public class ResponseEndpoint {

    private static final String NAMESPACE_URI = "http://response.dto.khryniewicki.com.pl";
    //    private final CurrencyRepository currencyRepository;
    private final NbpService NBPService;
    private final ValidateRequest validateRequest;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCurrencyRequest")
    @ResponsePayload
    public GetCurrencyResponse getExchangeRate(@RequestPayload GetCurrencyRequest request) {
        GetCurrencyResponse response = new GetCurrencyResponse();

        if (validateRequest.validateRequest(request, response)) {
            return response;
        }

        ExchangeRatesSeries exchangeObject = NBPService.getXMLFromApi(request);
        if (Objects.isNull(exchangeObject)){
            response.setMessage("Api does not provide information about this currency");
            return response;
        }
        response.setExchangeRatesSeries(exchangeObject);

        return response;
    }


}
