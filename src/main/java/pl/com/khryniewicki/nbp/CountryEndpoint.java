package pl.com.khryniewicki.nbp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.com.khryniewicki.classes.GetCurrencyRequest;
import pl.com.khryniewicki.classes.GetCurrencyResponse;

@Endpoint
public class CountryEndpoint {

    private static final String NAMESPACE_URI = "http://classes.khryniewicki.com.pl";

    private CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCurrencyRequest")
    @ResponsePayload
    public GetCurrencyResponse getCountry(@RequestPayload GetCurrencyRequest request) {
        GetCurrencyResponse response = new GetCurrencyResponse();
        response.setExchangeRatesSeries(countryRepository.findCountry(request.getCurrency()));

        return response;
    }
}
