package pl.com.khryniewicki.service;


import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.com.khryniewicki.dto.response.ExchangeRatesSeries;
import pl.com.khryniewicki.dto.response.GetCurrencyRequest;
import pl.com.khryniewicki.dto.response.GetCurrencyResponse;
import pl.com.khryniewicki.service.validation.ValidateRequest;
import pl.com.khryniewicki.service.validation.ValidateResponse;


@Endpoint
@RequiredArgsConstructor
public class ResponseEndpoint {

    private static final String NAMESPACE_URI = "http://response.dto.khryniewicki.com.pl";
    //    private final CurrencyRepository currencyRepository;
    private final ResponseService ResponseService;
    private final ValidateRequest validateRequest;
    private final ValidateResponse validateResponse;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCurrencyRequest")
    @ResponsePayload
    public GetCurrencyResponse getExchangeRate(@RequestPayload GetCurrencyRequest request) {
        GetCurrencyResponse response = new GetCurrencyResponse();
        System.out.println("Response1");
        if (validateRequest.validateRequest(request, response)) {
            return response;
        }
        System.out.println("Response2");

        ExchangeRatesSeries exchangeRates = ResponseService.getExchangeRates(request);

        System.out.println("Response3");

        if (validateResponse.validateResponse(response, exchangeRates)) {
            return response;
        }

        response.setExchangeRatesSeries(exchangeRates);

        return response;
    }




}
