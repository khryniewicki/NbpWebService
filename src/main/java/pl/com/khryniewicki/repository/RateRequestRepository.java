package pl.com.khryniewicki.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import pl.com.khryniewicki.dto.request.ExchangeRatesRequest;
import pl.com.khryniewicki.dto.request.RateRequest;
import pl.com.khryniewicki.dto.response.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Repository
public interface RateRequestRepository extends JpaRepository<RateRequest,Long> {


    //
//    private static final Map<String, ExchangeRatesSeries> currencies = new HashMap<>();
//
//    @PostConstruct
//    public void initData() {
//        pl.com.khryniewicki.dto.response.ExchangeRatesSeries exchangeRatesSeries = new pl.com.khryniewicki.dto.response.ExchangeRatesSeries();
//        exchangeRatesSeries.setCode(Code.USD);
//        exchangeRatesSeries.setCurrency("dolar amerykański");
//        Rates rates = new Rates();
//        LowestAskRate rateAsk = new LowestAskRate();
//        rateAsk.setAsk(4.23f);
//        rateAsk.setNo("sdsd332");
//        rateAsk.setEffectiveDate("12-03-12");
//        rates.setLowestAskRate(rateAsk);
//
//        HighestBidRate rateBid = new HighestBidRate();
//        rateBid.setBid(4.32f);
//        rateBid.setNo("343dfdsfds");
//        rateBid.setEffectiveDate("23-12-2012");
//        rates.setHighestBidRate(rateBid);
//        exchangeRatesSeries.setRates(rates);
//
//        currencies.put(exchangeRatesSeries.getCurrency(), exchangeRatesSeries);
//    }
//
//    public ExchangeRatesSeries findCountry(String currencyname) {
//        Assert.notNull(currencyname, "The country's name must not be null");
//        return currencies.get(currencyname);
//    }

}