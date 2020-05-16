package pl.com.khryniewicki.service.responseService;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.khryniewicki.dto.request.RequestHolder;
import pl.com.khryniewicki.dto.response.ExchangeRatesSeries;
import pl.com.khryniewicki.dto.response.GetCurrencyRequest;
import pl.com.khryniewicki.service.requestService.RateRequestServiceImp;
import pl.com.khryniewicki.service.responseService.responseMarshaller.ResponseMarshaller;
import pl.com.khryniewicki.util.RequestUtil;


@Service
@RequiredArgsConstructor
public class ResponseService {

    private final ResponseMarshaller responseMarshaller;
    private final RateRequestServiceImp rateRequestService;

    public ExchangeRatesSeries getExchangeRates(GetCurrencyRequest request) {
        RequestHolder requestHolder = RequestUtil.parseRequest(request);
        return isResponseAlreadyStored(requestHolder) ? getExchangeRatesFromDB(requestHolder) : getExchangeRatesFromApi(request);
    }

    public boolean isResponseAlreadyStored(RequestHolder requestHolder) {
        return rateRequestService.isRateRequestsStored(requestHolder);
    }


    public ExchangeRatesSeries getExchangeRatesFromDB(RequestHolder requestHolder) {
        return responseMarshaller.getExchangeRatesSeriesFromDB(requestHolder);
    }

    public ExchangeRatesSeries getExchangeRatesFromApi(GetCurrencyRequest request) {
        String currencyFullName = request.getCurrency();
        String startingDate = request.getStartingDate();
        String endingDate = request.getEndingDate();

        return responseMarshaller.getExchangeRatesSeriesFromApi(currencyFullName, startingDate, endingDate);
    }


}
