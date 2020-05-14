package pl.com.khryniewicki.service;

import pl.com.khryniewicki.dto.response.GetCurrencyRequest;

import javax.jws.WebService;

@WebService
public interface ITestWebService {

    GetCurrencyRequest get();

}
