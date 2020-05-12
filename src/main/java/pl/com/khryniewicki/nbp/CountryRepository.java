package pl.com.khryniewicki.nbp;


import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import com.korad.ExchangeRatesSeries;

import java.util.HashMap;
import java.util.Map;

@Component
public class CountryRepository {

    private static final Map<String, ExchangeRatesSeries> countries = new HashMap<>();


    public ExchangeRatesSeries findCurrency(String name) {
        Assert.notNull(name, "The country's name must not be null");
        return countries.get(name);
    }
}
