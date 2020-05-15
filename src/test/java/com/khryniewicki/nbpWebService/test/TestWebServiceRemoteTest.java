package com.khryniewicki.nbpWebService.test;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TestWebServiceRemoteTest {

    private static Endpoint endpoint;
    private static int port;

    @InjectMocks
    private static TestWebService sut = new TestWebService();

//    @Mock
//    private ICalculator calc;

    @BeforeClass
    public static void beforeClass() throws Exception {
        port = 8080;
        endpoint = Endpoint.publish("http://localhost:" + port + "/ws", sut);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        endpoint.stop();
    }

    @Test
    public void testAddNoMockSetup() throws Exception {
        URL wsdlUrl = new URL("http://localhost:" + 8080 + "/ws");
        QName serviceName = new QName("http://response.dto.khryniewicki.com.pl/", "getCurrencyRequest");
        Service service = Service.create(wsdlUrl, serviceName);


    }
}