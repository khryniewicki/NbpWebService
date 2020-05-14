package com.khryniewicki.nbpWebService.soap;


import lombok.RequiredArgsConstructor;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.com.khryniewicki.config.SOAPConnector;
import pl.com.khryniewicki.dto.response.*;
import pl.com.khryniewicki.service.WebServiceConfig;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor

public class SoapEndpointTest {
    public static SOAPConnector soapConnector;

    @Before
    public static void init(){
        WebServiceConfig webServiceConfig=new WebServiceConfig();
        soapConnector= webServiceConfig.soapConnector(webServiceConfig.marshaller());
    }

    @Test
    public void test1() {
        init();
        GetCurrencyRequest request = new GetCurrencyRequest();
        request.setCurrency("euro");
        request.setStartingDate("2020-03-12");
        request.setEndingDate("2020-05-11");


        GetCurrencyResponse get = (GetCurrencyResponse) soapConnector.callWebService("http://localhost:8080/ws", request);
        ExchangeRatesSeries testedExchangeRate = get.getExchangeRatesSeries();
        HighestBidRate highestBidRate = testedExchangeRate.getRates().getHighestBidRate();
        LowestAskRate lowestAskRate = testedExchangeRate.getRates().getLowestAskRate();


        Assertions.assertEquals("euro", testedExchangeRate.getCurrency());
        Assertions.assertEquals(4.5718f,highestBidRate.getBid());
        Assertions.assertEquals(4.3623f,lowestAskRate.getAsk() );

    }

    @Test
    public void test2() {
        init();
        GetCurrencyRequest request = new GetCurrencyRequest();
        request.setCurrency("dolar amerykański");
        request.setStartingDate("2020-02-01");
        request.setEndingDate("2020-03-05");


        GetCurrencyResponse get = (GetCurrencyResponse) soapConnector.callWebService("http://localhost:8080/ws", request);
        ExchangeRatesSeries testedExchangeRate = get.getExchangeRatesSeries();
        HighestBidRate highestBidRate = testedExchangeRate.getRates().getHighestBidRate();
        LowestAskRate lowestAskRate = testedExchangeRate.getRates().getLowestAskRate();


        Assertions.assertEquals("dolar amerykański", testedExchangeRate.getCurrency());
        Assertions.assertEquals(3.9286f,highestBidRate.getBid() );
        Assertions.assertEquals(3.8913f,lowestAskRate.getAsk() );

    }

    @Test
    public void test3() {
        init();
        GetCurrencyRequest request = new GetCurrencyRequest();
        request.setCurrency("rubel rosyjski");
        request.setStartingDate("2020-03-12");
        request.setEndingDate("2020-05-11");


        GetCurrencyResponse get = (GetCurrencyResponse) soapConnector.callWebService("http://localhost:8080/ws", request);
        String message = get.getMessage();
        ExchangeRatesSeries exchange = get.getExchangeRatesSeries();


        Assertions.assertEquals("Api does not provide information about this currency", message);
        Assertions.assertEquals(null, exchange);

    }

    @Test
    public void test4() {
        init();
        GetCurrencyRequest request = new GetCurrencyRequest();
        request.setCurrency("euro");
        request.setStartingDate("2020-14-12");
        request.setEndingDate("2020-05-11");


        GetCurrencyResponse get = (GetCurrencyResponse) soapConnector.callWebService("http://localhost:8080/ws", request);
        String message = get.getMessage();
        ExchangeRatesSeries exchange = get.getExchangeRatesSeries();


        Assertions.assertEquals("Invalid date", message);
        Assertions.assertEquals(null, exchange);

    }

    @Test
    public void test5() {
        init();
        GetCurrencyRequest request = new GetCurrencyRequest();
        request.setCurrency("euro");
        request.setStartingDate("2020-12-12");
        request.setEndingDate("2020-25-11");


        GetCurrencyResponse get = (GetCurrencyResponse) soapConnector.callWebService("http://localhost:8080/ws", request);
        String message = get.getMessage();
        ExchangeRatesSeries exchange = get.getExchangeRatesSeries();



        Assertions.assertEquals("Invalid date", message);
        Assertions.assertEquals(null, exchange);

    }

    @Test
    public void test6() {
        init();
        GetCurrencyRequest request = new GetCurrencyRequest();
        request.setCurrency("europa");
        request.setStartingDate("2020-03-12");
        request.setEndingDate("2020-02-11");


        GetCurrencyResponse get = (GetCurrencyResponse) soapConnector.callWebService("http://localhost:8080/ws", request);
        String message = get.getMessage();
        ExchangeRatesSeries exchange = get.getExchangeRatesSeries();


        Assertions.assertEquals("Invalid currency name", message);
        Assertions.assertEquals(null, exchange);

    }

    @Test
    public void test7() {
        init();
        GetCurrencyRequest request = new GetCurrencyRequest();
        request.setCurrency("euro");
        request.setStartingDate(null);
        request.setEndingDate("2020-02-11");


        GetCurrencyResponse get = (GetCurrencyResponse) soapConnector.callWebService("http://localhost:8080/ws", request);
        String message = get.getMessage();
        ExchangeRatesSeries exchange = get.getExchangeRatesSeries();


        Assertions.assertEquals("Date not found", message);
        Assertions.assertEquals(null, exchange);
    }

    @Test
    public void test8() {
        init();
        GetCurrencyRequest request = new GetCurrencyRequest();
        request.setCurrency(null);
        request.setStartingDate("2020-03-11");
        request.setEndingDate("2020-02-11");


        GetCurrencyResponse get = (GetCurrencyResponse) soapConnector.callWebService("http://localhost:8080/ws", request);
        String message = get.getMessage();
        ExchangeRatesSeries exchange = get.getExchangeRatesSeries();


        Assertions.assertEquals("No currency name found", message);
        Assertions.assertEquals(null, exchange);
    }
}
