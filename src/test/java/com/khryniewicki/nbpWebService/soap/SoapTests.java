package com.khryniewicki.nbpWebService.soap;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import pl.com.khryniewicki.config.SOAPConnector;
import pl.com.khryniewicki.request.CodeRequest;
import pl.com.khryniewicki.response.ExchangeRatesSeries;
import pl.com.khryniewicki.response.GetCurrencyRequest;
import pl.com.khryniewicki.response.GetCurrencyResponse;
import pl.com.khryniewicki.response.HighestBidRate;

import javax.annotation.Resource;

@TestComponent
public class SoapTests {

//    @Autowired
//    private SOAPConnector soapConnector;
//
//    @Test
//    public void test(){
//        GetCurrencyRequest request = new GetCurrencyRequest();
//        request.setCurrency("euro");
//        request.setStartingDate("2020-03-12");
//        request.setEndingDate("2020-05-11");
//        GetCurrencyResponse get=(GetCurrencyResponse) soapConnector.callWebService("http://localhost:8080/ws", request);
//        ExchangeRatesSeries testedExchangeRate = get.getExchangeRatesSeries();
//        HighestBidRate highestBidRate = testedExchangeRate.getRates().getHighestBidRate();
//
//
//        Assertions.assertEquals("euro",testedExchangeRate.getCurrency());
//        Assertions.assertEquals(CodeRequest.EUR,testedExchangeRate.getCode());
//        Assertions.assertEquals(highestBidRate.getBid(),4.31f);
//
//    }
}
