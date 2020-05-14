package com.khryniewicki.nbpWebService.soap;

import lombok.RequiredArgsConstructor;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import pl.com.khryniewicki.config.SOAPConnector;


@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class SoapTests {
//    private static SOAPConnector soapConnector;
//    @BeforeClass
//    public static void beforeClass() throws Exception {
//        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//        marshaller.setContextPath("pl.com.khryniewicki.dto.response");
//        soapConnector = new SOAPConnector();
//        soapConnector.setMarshaller(marshaller);
//        soapConnector.setUnmarshaller(marshaller);
//    }
//
//    @Test
//    public void test() {
//        GetCurrencyRequest request = new GetCurrencyRequest();
//        request.setCurrency("euro");
//        request.setStartingDate("2020-03-12");
//        request.setEndingDate("2020-05-11");
//
//
//        GetCurrencyResponse get = (GetCurrencyResponse) soapConnector.callWebService("http://localhost:8080/ws", request);
//        ExchangeRatesSeries testedExchangeRate = get.getExchangeRatesSeries();
//        HighestBidRate highestBidRate = testedExchangeRate.getRates().getHighestBidRate();
//
//
//        Assertions.assertEquals("euro", testedExchangeRate.getCurrency());
////        Assertions.assertEquals(CodeRequest.EUR,testedExchangeRate.getCode());
////        Assertions.assertEquals(highestBidRate.getBid(),4.31f);
//
//    }
}
