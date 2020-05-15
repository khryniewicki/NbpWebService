package pl.com.khryniewicki.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.khryniewicki.dto.request.*;
import pl.com.khryniewicki.dto.response.*;
import pl.com.khryniewicki.repository.RateEntityService;
import pl.com.khryniewicki.util.RequestUtil;


@Service
@RequiredArgsConstructor
public class ResponseService {

    private final ExchangeRatesMarshaller exchangeRatesMarshaller;
    private final RateEntityService rateEntityService;
    private final RequestMarshaller requestMarshaller;

    public ExchangeRatesSeries getExchangeRates(GetCurrencyRequest request) {
        RequestHolder requestHolder = RequestUtil.parseRequest(request);
        return isResponseAlreadyStored(requestHolder)? getExchangeRatesFromDB(requestHolder):getExchangeRatesFromApi(request);
    }

    private boolean isResponseAlreadyStored(RequestHolder requestHolder) {
        return rateEntityService.isRateRequestsStored(requestHolder);
    }


    private ExchangeRatesSeries getExchangeRatesFromDB(RequestHolder requestHolder) {
        ExchangeRatesRequest exchangeRatesSeries = requestMarshaller.exchangeRatesMarshaller(requestHolder);
        return exchangeRatesMarshaller.prepareExchangeRatesFromDB(exchangeRatesSeries);
    }

    public ExchangeRatesSeries getExchangeRatesFromApi(GetCurrencyRequest request) {
        String currencyFullName = request.getCurrency();
        String startingDate = request.getStartingDate();
        String endingDate = request.getEndingDate();

        return exchangeRatesMarshaller.getExchangeRatesSeries(currencyFullName, startingDate, endingDate);
    }


}
