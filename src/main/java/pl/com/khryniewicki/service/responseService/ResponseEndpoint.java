package pl.com.khryniewicki.service.responseService;


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
    private final ResponseService ResponseService;
    private final ValidateRequest validateRequest;
    private final ValidateResponse validateResponse;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCurrencyRequest")
    @ResponsePayload
    public GetCurrencyResponse getExchangeRate(@RequestPayload GetCurrencyRequest request) {
        GetCurrencyResponse response = new GetCurrencyResponse();

        if (validateRequest.validateRequest(request, response)) {
            return response;
        }

        ExchangeRatesSeries exchangeRates = ResponseService.getExchangeRates(request);

        if (validateResponse.validateResponse(response, exchangeRates)) {
            return response;
        }

        response.setExchangeRatesSeries(exchangeRates);

        return response;
    }




}
