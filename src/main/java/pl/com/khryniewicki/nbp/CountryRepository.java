package pl.com.khryniewicki.nbp;


import com.baeldung.springsoap.client.gen.Country;
import com.baeldung.springsoap.client.gen.Currency;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import pl.com.khryniewicki.classes.Code;
import pl.com.khryniewicki.classes.ExchangeRatesSeries;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class CountryRepository {

    private static final Map<String, ExchangeRatesSeries> countries = new HashMap<>();

    @PostConstruct
    public void initData() {
        ExchangeRatesSeries usd = new ExchangeRatesSeries();
        usd.setCode(Code.USD);
        usd.setCurrency("dolar amerykański");

        countries.put(usd.getCurrency(), usd);
    }

    public ExchangeRatesSeries findCountry(String currencyname) {
        Assert.notNull(currencyname, "The country's name must not be null");
        return countries.get(currencyname);
    }
}
