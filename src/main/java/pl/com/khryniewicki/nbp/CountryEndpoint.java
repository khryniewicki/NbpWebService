package pl.com.khryniewicki.nbp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.com.khryniewicki.response.GetCurrencyRequest;
import pl.com.khryniewicki.response.GetCurrencyResponse;

@Endpoint
public class CountryEndpoint {

    private static final String NAMESPACE_URI = "http://response.khryniewicki.com.pl";

    private CurrencyRepository currencyRepository;

    @Autowired
    public CountryEndpoint(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCurrencyRequest")
    @ResponsePayload
    public pl.com.khryniewicki.response.GetCurrencyResponse getCountry(@RequestPayload GetCurrencyRequest request) {
        GetCurrencyResponse getCurrencyResponse = new GetCurrencyResponse();
        getCurrencyResponse.setExchangeRatesSeries (currencyRepository.findCountry(request.getCurrency()));
        return getCurrencyResponse;
    }
}
