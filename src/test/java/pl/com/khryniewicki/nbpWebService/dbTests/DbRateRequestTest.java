package pl.com.khryniewicki.nbpWebService.dbTests;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.com.khryniewicki.dto.repository.RateRequestRepository;
import pl.com.khryniewicki.dto.request.ExchangeRatesRequest;
import pl.com.khryniewicki.dto.request.RateRequest;
import pl.com.khryniewicki.dto.response.Code;
import pl.com.khryniewicki.service.requestService.ExchangeRatesService;
import pl.com.khryniewicki.service.requestService.RateRequestService;
import pl.com.khryniewicki.service.requestService.RateRequestServiceImp;

import java.util.GregorianCalendar;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@RequiredArgsConstructor
public class DbRateRequestTest {
    ExchangeRatesRequest exchange;
    RateRequest rateRequest;
    GregorianCalendar startDate;

    @TestConfiguration
    static class RateRequestServiceTestContextConfiguration {
        @Bean
        public RateRequestService rateRequestService() {
            return new RateRequestServiceImp();
        }
    }

    @MockBean
    private RateRequestRepository rateRequestRepository;
    @MockBean
    private ExchangeRatesService exchangeRatesService;
    @Autowired
    private RateRequestService rateRequestService;

    @BeforeEach
    public void init() {
        exchange = new ExchangeRatesRequest();
        rateRequest = new RateRequest();
        startDate = new GregorianCalendar(2018, 6, 27, 0, 0, 0);

        String name = "dolar amerykański";
        Code code = Code.USD;
        exchange.setCurrency(name);
        exchange.setCode(code);

        rateRequest.setExchange(exchange);
        rateRequest.setAsk(4.32f);
        rateRequest.setBid(4.21f);
        rateRequest.setEffectiveDate(startDate);

    }

    @Test
    public void testFindRateRequestByExchangeAndEffectiveDate() {

        Mockito.when(rateRequestRepository.findRateRequestByExchangeAndEffectiveDate(exchange, startDate))
                .thenReturn(java.util.Optional.of(rateRequest));

        Optional<RateRequest> found = rateRequestService.getRateRequestByExchangeAndDate(exchange, startDate);

        Assertions.assertNotNull(rateRequest);
        Assertions.assertEquals(rateRequest, found.get());
        Assertions.assertEquals(4.21f, rateRequest.getBid());
        Assertions.assertEquals(4.32f, rateRequest.getAsk());
    }

    @Test
    public void testFindRateRequestByEffectiveDate() {

        Mockito.when(rateRequestRepository.findByEffectiveDate(startDate))
                .thenReturn(java.util.Optional.of(rateRequest));

        Optional<RateRequest> found = rateRequestService.findByEffectiveDate(startDate);

        Assertions.assertNotNull(rateRequest);
        Assertions.assertEquals(rateRequest, found.get());
        Assertions.assertEquals(4.21f, rateRequest.getBid());
        Assertions.assertEquals(4.32f, rateRequest.getAsk());
    }

}
