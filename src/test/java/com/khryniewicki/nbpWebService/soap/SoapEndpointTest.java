package com.khryniewicki.nbpWebService.soap;


import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.com.khryniewicki.config.SOAPConnector;
import pl.com.khryniewicki.dto.response.*;
import pl.com.khryniewicki.service.WebServiceConfig;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class SoapEndpointTest {
    public static final String HTTP_LOCALHOST_8080_WS = "http://localhost:8080/ws";
    public static SOAPConnector soapConnector;

    @BeforeEach
    public void init() {
        WebServiceConfig webServiceConfig = new WebServiceConfig();
        soapConnector = webServiceConfig.soapConnector(webServiceConfig.marshaller());


    }

    @Test
    public void test1() {
        GetCurrencyRequest request = new GetCurrencyRequest();
        request.setCurrency("euro");
        request.setStartingDate("2020-03-12");
        request.setEndingDate("2020-05-11");


        GetCurrencyResponse get = (GetCurrencyResponse) soapConnector.callWebService("http://localhost:8080/ws", request);
        ExchangeRatesSeries testedExchangeRate = get.getExchangeRatesSeries();
        HighestBidRate highestBidRate = testedExchangeRate.getRates().getHighestBidRate();
        LowestAskRate lowestAskRate = testedExchangeRate.getRates().getLowestAskRate();


        Assertions.assertEquals("euro", testedExchangeRate.getCurrency());
        Assertions.assertEquals(4.5718f, highestBidRate.getBid());
        Assertions.assertEquals(4.3623f, lowestAskRate.getAsk());

    }

    @Test
    public void test2() {

        GetCurrencyRequest request = new GetCurrencyRequest();
        request.setCurrency("dolar amerykański");
        request.setStartingDate("2020-02-01");
        request.setEndingDate("2020-03-05");


        GetCurrencyResponse get = (GetCurrencyResponse) soapConnector.callWebService(HTTP_LOCALHOST_8080_WS, request);
        ExchangeRatesSeries testedExchangeRate = get.getExchangeRatesSeries();
        HighestBidRate highestBidRate = testedExchangeRate.getRates().getHighestBidRate();
        LowestAskRate lowestAskRate = testedExchangeRate.getRates().getLowestAskRate();


        Assertions.assertEquals("dolar amerykański", testedExchangeRate.getCurrency());
        Assertions.assertEquals(3.9286f, highestBidRate.getBid());
        Assertions.assertEquals(3.8913f, lowestAskRate.getAsk());

    }

    @Test
    public void test3() {

        GetCurrencyRequest request = new GetCurrencyRequest();
        request.setCurrency("rubel rosyjski");
        request.setStartingDate("2020-03-12");
        request.setEndingDate("2020-05-11");


        GetCurrencyResponse get = (GetCurrencyResponse) soapConnector.callWebService(HTTP_LOCALHOST_8080_WS, request);
        String message = get.getMessage();
        ExchangeRatesSeries exchange = get.getExchangeRatesSeries();


        Assertions.assertEquals("Api does not provide information about this currency", message);
        Assertions.assertEquals(null, exchange);

    }

    @Test
    public void test4() {
        GetCurrencyRequest request = new GetCurrencyRequest();
        request.setCurrency("euro");
        request.setStartingDate("2020-14-12");
        request.setEndingDate("2020-05-11");


        GetCurrencyResponse get = (GetCurrencyResponse) soapConnector.callWebService(HTTP_LOCALHOST_8080_WS, request);
        String message = get.getMessage();
        ExchangeRatesSeries exchange = get.getExchangeRatesSeries();


        Assertions.assertEquals("Invalid date", message);
        Assertions.assertEquals(null, exchange);

    }

    @Test
    public void test5() {

        GetCurrencyRequest request = new GetCurrencyRequest();
        request.setCurrency("euro");
        request.setStartingDate("2020-12-12");
        request.setEndingDate("2020-25-11");


        GetCurrencyResponse get = (GetCurrencyResponse) soapConnector.callWebService(HTTP_LOCALHOST_8080_WS, request);
        String message = get.getMessage();
        ExchangeRatesSeries exchange = get.getExchangeRatesSeries();


        Assertions.assertEquals("Invalid date", message);
        Assertions.assertEquals(null, exchange);

    }

    @Test
    public void test6() {

        GetCurrencyRequest request = new GetCurrencyRequest();
        request.setCurrency("europa");
        request.setStartingDate("2020-03-12");
        request.setEndingDate("2020-04-11");


        GetCurrencyResponse get = (GetCurrencyResponse) soapConnector.callWebService(HTTP_LOCALHOST_8080_WS, request);
        String message = get.getMessage();
        ExchangeRatesSeries exchange = get.getExchangeRatesSeries();


        Assertions.assertEquals("Invalid currency name", message);
        Assertions.assertEquals(null, exchange);

    }
    @Test
    public void test7() {

        GetCurrencyRequest request = new GetCurrencyRequest();
        request.setCurrency("euro");
        request.setStartingDate("2020-03-12");
        request.setEndingDate("2020-03-11");


        GetCurrencyResponse get = (GetCurrencyResponse) soapConnector.callWebService("http://localhost:8080/ws", request);
        String message = get.getMessage();
        ExchangeRatesSeries exchange = get.getExchangeRatesSeries();


        Assertions.assertEquals("Invalid date", message);
        Assertions.assertEquals(null, exchange);

    }
    @Test
    public void test8() {
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
    public void test9() {
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
