package pl.com.khryniewicki.nbpWebService.dbTests;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.com.khryniewicki.dto.repository.ExchangeRatesRequestRepository;
import pl.com.khryniewicki.dto.request.ExchangeRatesRequest;
import pl.com.khryniewicki.dto.response.Code;
import pl.com.khryniewicki.service.requestService.ExchangeRatesService;
import pl.com.khryniewicki.service.requestService.ExchangeRatesServiceImp;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@RequiredArgsConstructor
public class DbExchangeRateSeriesTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public ExchangeRatesService exchangeRatesService() {
            return new ExchangeRatesServiceImp();
        }
    }

    @MockBean
    private ExchangeRatesRequestRepository exchangeRatesRequestRepository;

    @Autowired
    private ExchangeRatesService exchangeRatesService;


    @Test
    public void testExchangeRatesWithCurrencyDolar() {
        ExchangeRatesRequest exchange = new ExchangeRatesRequest();
        String name = "dolar amerykański";
        Code code = Code.USD;
        exchange.setCurrency(name);
        exchange.setCode(code);
        Mockito.when(exchangeRatesRequestRepository.findExchangeRatesRequestByCurrency(exchange.getCurrency()))
                .thenReturn(exchange);

        ExchangeRatesRequest found = exchangeRatesService.findByCurrency(name);

        Assertions.assertNotNull(found);
        Assertions.assertEquals(exchange, found);
        Assertions.assertEquals(name, exchange.getCurrency());
        Assertions.assertEquals(code, exchange.getCode());
    }

    @Test
    public void testExchangeRatesWithCurrencyEuro() {
        ExchangeRatesRequest exchange = new ExchangeRatesRequest();
        String name = "euro";
        Code code = Code.EUR;
        exchange.setCurrency(name);
        exchange.setCode(code);
        Mockito.when(exchangeRatesRequestRepository.findExchangeRatesRequestByCurrency(exchange.getCurrency()))
                .thenReturn(exchange);

        ExchangeRatesRequest found = exchangeRatesService.findByCurrency(name);

        Assertions.assertNotNull(found);
        Assertions.assertEquals(exchange, found);
        Assertions.assertEquals(name, exchange.getCurrency());
        Assertions.assertEquals(code, exchange.getCode());
    }


}
