package pl.com.khryniewicki.nbp;


import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.com.khryniewicki.classes.ExchangeRatesSeries;
import pl.com.khryniewicki.classes.GetCurrencyRequest;
import pl.com.khryniewicki.classes.GetCurrencyResponse;


@Endpoint
@RequiredArgsConstructor
public class CountryEndpoint {

    private static final String NAMESPACE_URI = "http://www.khryniewicki.com.pl/classes";
    private final CountryRepository countryRepository;
    private final ApiNbpService ApiNbpService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCurrencyRequest")
    @ResponsePayload
    public GetCurrencyResponse getCurrency(@RequestPayload GetCurrencyRequest request) {
        GetCurrencyResponse response = new GetCurrencyResponse();
        pl.com.khryniewicki.nbp.ApiNbpService apiNbpService = new ApiNbpService();
        ExchangeRatesSeries xmlFromApi = apiNbpService.getXMLFromApi(request.getCurrency());
        response.setExchangeRatesSeries(xmlFromApi);
        return response;
    }


}
