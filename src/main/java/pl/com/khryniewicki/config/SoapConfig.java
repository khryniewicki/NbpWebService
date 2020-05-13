package pl.com.khryniewicki.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapConfig {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

        marshaller.setContextPath("pl.com.khryniewicki.response");
        return marshaller;
    }

    @Bean
    public SoapWebServiceGateway soapConnector(Jaxb2Marshaller marshaller) {
        SoapWebServiceGateway client = new SoapWebServiceGateway();
        client.setDefaultUri("http://localhost:8080/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
