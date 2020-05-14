package pl.com.khryniewicki.service;

import pl.com.khryniewicki.dto.response.GetCurrencyRequest;

import javax.jws.WebService;

@WebService
public class TestWebService implements ITestWebService {


    @Override
    public GetCurrencyRequest get() {
        GetCurrencyRequest request = new GetCurrencyRequest();
            request.setCurrency("euro");
            request.setStartingDate("2020-03-12");
            request.setEndingDate("2020-05-11");
            return request;
    }
}


