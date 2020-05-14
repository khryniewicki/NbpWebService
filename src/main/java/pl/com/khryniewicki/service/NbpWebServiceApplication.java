package pl.com.khryniewicki.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.com.khryniewicki.config.SOAPConnector;
import pl.com.khryniewicki.dto.response.GetCurrencyRequest;
import pl.com.khryniewicki.dto.response.GetCurrencyResponse;


@SpringBootApplication
public class NbpWebServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NbpWebServiceApplication.class, args);

    }

//
//    @Bean
//    CommandLineRunner lookup(SOAPConnector soapConnector) {
//        return args -> {
//
//            GetCurrencyRequest request = new GetCurrencyRequest();
//            request.setCurrency("euro");
//            request.setStartingDate("2020-03-12");
//            request.setEndingDate("2020-05-11");
//
//            GetCurrencyResponse get=(GetCurrencyResponse)soapConnector.callWebService("http://localhost:8080/ws", request);
//            System.out.println(get.getExchangeRatesSeries().getCurrency());
//
//        };
//    }

}


