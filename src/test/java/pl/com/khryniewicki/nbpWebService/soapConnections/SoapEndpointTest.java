package pl.com.khryniewicki.nbpWebService.soapConnections;


import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.com.khryniewicki.config.SoapConnector;
import pl.com.khryniewicki.dto.response.*;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class SoapEndpointTest {

    //In order to run tests, type "mvn spring-boot:run" in terminal;

    public static final String HTTP_LOCALHOST_8080_WS = "http://localhost:8080/ws";
    public static SoapConnector soapConnector;

    @BeforeEach
    public void before() throws IOException {
        soapConnector = new SoapConnector();
        soapConnector.connect();
    }

    @AfterEach
    public void after() throws IOException {
        soapConnector.disconnect();
    }

    @Test
    public void testValidRequestWithEuro() {
        GetCurrencyRequest request = new GetCurrencyRequest();

        request.setCurrency("euro");
        request.setStartingDate("2020-03-12");
        request.setEndingDate("2020-05-11");


        GetCurrencyResponse get = (GetCurrencyResponse) soapConnector.sendAndReceive(HTTP_LOCALHOST_8080_WS, request);

        ExchangeRatesSeries testedExchangeRate = get.getExchangeRatesSeries();
        HighestBidRate highestBidRate = testedExchangeRate.getRates().getHighestBidRate();
        LowestAskRate lowestAskRate = testedExchangeRate.getRates().getLowestAskRate();


        Assertions.assertEquals("euro", testedExchangeRate.getCurrency());
        Assertions.assertEquals(4.5718f, highestBidRate.getBid());
        Assertions.assertEquals(4.3623f, lowestAskRate.getAsk());

    }

    @Test
    public void testValidRequestWithDolar() {

        GetCurrencyRequest request = new GetCurrencyRequest();
        request.setCurrency("dolar amerykański");
        request.setStartingDate("2020-02-01");
        request.setEndingDate("2020-03-05");


        GetCurrencyResponse get = (GetCurrencyResponse) soapConnector.sendAndReceive(HTTP_LOCALHOST_8080_WS, request);
        ExchangeRatesSeries testedExchangeRate = get.getExchangeRatesSeries();
        HighestBidRate highestBidRate = testedExchangeRate.getRates().getHighestBidRate();
        LowestAskRate lowestAskRate = testedExchangeRate.getRates().getLowestAskRate();


        Assertions.assertEquals("dolar amerykański", testedExchangeRate.getCurrency());
        Assertions.assertEquals(3.9286f, highestBidRate.getBid());
        Assertions.assertEquals(3.8913f, lowestAskRate.getAsk());

    }

    @Test
    public void testInvalidRequestWithRubel() {

        GetCurrencyRequest request = new GetCurrencyRequest();
        request.setCurrency("rubel rosyjski");
        request.setStartingDate("2020-03-12");
        request.setEndingDate("2020-05-11");


        GetCurrencyResponse get = (GetCurrencyResponse) soapConnector.sendAndReceive(HTTP_LOCALHOST_8080_WS, request);
        String message = get.getMessage();
        ExchangeRatesSeries exchange = get.getExchangeRatesSeries();


        Assertions.assertEquals("Api does not provide information about this currency", message);
        Assertions.assertNull(exchange);

    }

    @Test
    public void testRequestWithInvalidDateFormat() {
        GetCurrencyRequest request = new GetCurrencyRequest();
        request.setCurrency("euro");
        request.setStartingDate("2020.14-12");
        request.setEndingDate("2020-05-11");


        GetCurrencyResponse get = (GetCurrencyResponse) soapConnector.sendAndReceive(HTTP_LOCALHOST_8080_WS, request);
        String message = get.getMessage();
        ExchangeRatesSeries exchange = get.getExchangeRatesSeries();


        Assertions.assertEquals("Invalid date", message);
        Assertions.assertNull(exchange);

    }

    @Test
    public void testRequestWithInvalidMonth() {

        GetCurrencyRequest request = new GetCurrencyRequest();
        request.setCurrency("euro");
        request.setStartingDate("2020-12-12");
        request.setEndingDate("2020-25-11");


        GetCurrencyResponse get = (GetCurrencyResponse) soapConnector.sendAndReceive(HTTP_LOCALHOST_8080_WS, request);
        String message = get.getMessage();
        ExchangeRatesSeries exchange = get.getExchangeRatesSeries();


        Assertions.assertEquals("Invalid date", message);
        Assertions.assertNull(exchange);

    }

    @Test
    public void testRequestWithInvalidCurrencyName() {

        GetCurrencyRequest request = new GetCurrencyRequest();
        request.setCurrency("europa");
        request.setStartingDate("2020-03-12");
        request.setEndingDate("2020-04-11");


        GetCurrencyResponse get = (GetCurrencyResponse) soapConnector.sendAndReceive(HTTP_LOCALHOST_8080_WS, request);
        String message = get.getMessage();
        ExchangeRatesSeries exchange = get.getExchangeRatesSeries();


        Assertions.assertEquals("Invalid currency name", message);
        Assertions.assertNull(exchange);

    }

    @Test
    public void testRequestWithUnchronogicalDates() {

        GetCurrencyRequest request = new GetCurrencyRequest();
        request.setCurrency("euro");
        request.setStartingDate("2020-03-12");
        request.setEndingDate("2020-03-11");


        GetCurrencyResponse get = (GetCurrencyResponse) soapConnector.sendAndReceive(HTTP_LOCALHOST_8080_WS, request);
        String message = get.getMessage();
        ExchangeRatesSeries exchange = get.getExchangeRatesSeries();


        Assertions.assertEquals("Invalid date", message);
        Assertions.assertNull(exchange);

    }

    @Test
    public void testRequestWithInvalidStartingDate() {
        GetCurrencyRequest request = new GetCurrencyRequest();
        request.setCurrency("euro");
        request.setStartingDate(null);
        request.setEndingDate("2020-02-11");


        GetCurrencyResponse get = (GetCurrencyResponse) soapConnector.sendAndReceive(HTTP_LOCALHOST_8080_WS, request);
        String message = get.getMessage();
        ExchangeRatesSeries exchange = get.getExchangeRatesSeries();


        Assertions.assertEquals("Date not found", message);
        Assertions.assertNull(exchange);
    }

    @Test
    public void testRequestWithEmptyEndingDate() {
        GetCurrencyRequest request = new GetCurrencyRequest();
        request.setCurrency("euro");
        request.setStartingDate("2020-02-11");
        request.setEndingDate(null);


        GetCurrencyResponse get = (GetCurrencyResponse) soapConnector.sendAndReceive(HTTP_LOCALHOST_8080_WS, request);
        String message = get.getMessage();
        ExchangeRatesSeries exchange = get.getExchangeRatesSeries();


        Assertions.assertEquals("Date not found", message);
        Assertions.assertNull(exchange);
    }

    @Test
    public void testRequestWithEmptyCurrencyName() {
        GetCurrencyRequest request = new GetCurrencyRequest();
        request.setCurrency(null);
        request.setStartingDate("2020-03-11");
        request.setEndingDate("2020-02-11");


        GetCurrencyResponse get = (GetCurrencyResponse) soapConnector.sendAndReceive(HTTP_LOCALHOST_8080_WS, request);
        String message = get.getMessage();
        ExchangeRatesSeries exchange = get.getExchangeRatesSeries();


        Assertions.assertEquals("No currency name found", message);
        Assertions.assertNull(exchange);
    }

    @Test
    public void testRequestWithObsolateDates() {
        GetCurrencyRequest request = new GetCurrencyRequest();

        request.setCurrency("euro");
        request.setStartingDate("2001-01-03");
        request.setEndingDate("2001-02-03");


        GetCurrencyResponse get = (GetCurrencyResponse) soapConnector.sendAndReceive(HTTP_LOCALHOST_8080_WS, request);

        String message = get.getMessage();
        ExchangeRatesSeries exchange = get.getExchangeRatesSeries();

        Assertions.assertEquals("Invalid date", message);
        Assertions.assertNull(exchange);

    }

    @Test
    public void testRequestWithFutureDates() {
        GetCurrencyRequest request = new GetCurrencyRequest();

        request.setCurrency("euro");
        request.setStartingDate("2020-03-12");
        request.setEndingDate("2020-08-11");


        GetCurrencyResponse get = (GetCurrencyResponse) soapConnector.sendAndReceive(HTTP_LOCALHOST_8080_WS, request);

        String message = get.getMessage();
        ExchangeRatesSeries exchange = get.getExchangeRatesSeries();

        Assertions.assertEquals("Invalid date", message);
        Assertions.assertNull(exchange);

    }

    @Test
    public void testRequestWithInvalidDatesRange() {
        GetCurrencyRequest request = new GetCurrencyRequest();

        request.setCurrency("euro");
        request.setStartingDate("2018-05-12");
        request.setEndingDate("2020-03-11");


        GetCurrencyResponse get = (GetCurrencyResponse) soapConnector.sendAndReceive(HTTP_LOCALHOST_8080_WS, request);

        String message = get.getMessage();
        ExchangeRatesSeries exchange = get.getExchangeRatesSeries();

        Assertions.assertEquals("Invalid dates range", message);
        Assertions.assertNull(exchange);

    }
}
