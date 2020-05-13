package pl.com.khryniewicki.nbp;


import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import pl.com.khryniewicki.response.ExchangeRatesSeries;
import pl.com.khryniewicki.response.RateAsk;
import pl.com.khryniewicki.response.RateBid;
import pl.com.khryniewicki.response.Rates;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class CurrencyRepository {

    private static final Map<String, pl.com.khryniewicki.response.ExchangeRatesSeries> currencies = new HashMap<>();

    @PostConstruct
    public void initData() {
        pl.com.khryniewicki.response.ExchangeRatesSeries exchangeRatesSeries = new pl.com.khryniewicki.response.ExchangeRatesSeries();
        exchangeRatesSeries.setCode(pl.com.khryniewicki.response.Code.USD);
        exchangeRatesSeries.setCurrency("dolar amerykański");
        Rates rates = new Rates();
        RateAsk rateAsk = new RateAsk();
        rateAsk.setAsk(4.23f);
        rateAsk.setEffectiveDate("sdsd332");
        rateAsk.setEffectiveDate("12-03-12");
        rates.setBestAsk(rateAsk);

        RateBid rateBid = new RateBid();
        rateBid.setBid(4.32f);
        rateBid.setEffectiveDate("343dfdsfds");
        rateBid.setEffectiveDate("23-12-2012");
        rates.setBestBid(rateBid);
        exchangeRatesSeries.setRates(rates);

        currencies.put(exchangeRatesSeries.getCurrency(), exchangeRatesSeries);
    }

    public ExchangeRatesSeries findCountry(String currencyname) {
        Assert.notNull(currencyname, "The country's name must not be null");
        return currencies.get(currencyname);
    }
}
