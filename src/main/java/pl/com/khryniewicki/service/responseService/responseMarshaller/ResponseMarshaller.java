package pl.com.khryniewicki.service.responseService.responseMarshaller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.khryniewicki.dto.request.ExchangeRatesRequest;
import pl.com.khryniewicki.dto.request.RequestHolder;
import pl.com.khryniewicki.dto.response.ExchangeRatesSeries;
import pl.com.khryniewicki.service.responseService.dbService.DBservice;
import pl.com.khryniewicki.service.responseService.nbpservice.NbpService;

@Service
@RequiredArgsConstructor
public class ResponseMarshaller {
    private final NbpService nbpService;
    private final DBservice dBservice;
    private final ExchangeRatesMarshaller exchangeRatesMarshaller;

    public ExchangeRatesSeries getExchangeRatesSeriesFromApi(String currencyFullName, String startingDate, String endingDate) {
        return prepareExchangeRatesSeries(currencyFullName, startingDate, endingDate);
    }

    public ExchangeRatesSeries getExchangeRatesSeriesFromDB(RequestHolder requestHolder) {
        return prepareExchangeRatesFromDB(requestHolder);
    }

    private ExchangeRatesSeries prepareExchangeRatesSeries(String currencyFullName, String startingDate, String endingDate) {
        String fullXML = nbpService.parseXmlFromNBPApiToString(currencyFullName, startingDate, endingDate);

        if (fullXML.isEmpty()) {
            return null;
        }

        ExchangeRatesRequest exchangeRatesRequest = nbpService.unmarshallStringFromApi(fullXML);
        dBservice.saveExchangeRates(exchangeRatesRequest);
        return exchangeRatesMarshaller.exchangeRatesMarshaller(exchangeRatesRequest);
    }


    private ExchangeRatesSeries prepareExchangeRatesFromDB(RequestHolder requestHolder) {
        ExchangeRatesRequest exchangeRatesRequest = dBservice.findExchangeRates(requestHolder);
        return exchangeRatesMarshaller.exchangeRatesMarshaller(exchangeRatesRequest);
    }
}
