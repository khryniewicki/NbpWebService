package pl.com.khryniewicki.service.responseService.responseMarshaller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.khryniewicki.dto.request.ExchangeRatesRequest;
import pl.com.khryniewicki.dto.request.RequestHolder;
import pl.com.khryniewicki.dto.response.ExchangeRatesSeries;
import pl.com.khryniewicki.service.responseService.dbService.DBservice;
import pl.com.khryniewicki.service.responseService.nbpservice.NbpService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResponseMarshaller {
    private final NbpService nbpService;
    private final DBservice dBservice;
    private final ExchangeRatesMarshaller exchangeRatesMarshaller;

    public ExchangeRatesSeries getExchangeRatesSeriesFromApi(String currencyFullName, String startingDate, String endingDate) {
        Optional<ExchangeRatesSeries> optional = prepareExchangeRatesSeries(currencyFullName, startingDate, endingDate);
        if (optional.isPresent()) return optional.get();
        else throw new NullPointerException("Api does not provide information about this currency") ;
    }

    public ExchangeRatesSeries getExchangeRatesSeriesFromDB(RequestHolder requestHolder) {
        return prepareExchangeRatesFromDB(requestHolder);
    }

    private Optional<ExchangeRatesSeries> prepareExchangeRatesSeries(String currencyFullName, String startingDate, String endingDate) {
        String fullXML = nbpService.parseXmlFromNBPApiToString(currencyFullName, startingDate, endingDate);

        if (fullXML.isEmpty()) {
            return Optional.empty();
        }

        ExchangeRatesRequest exchangeRatesRequest = nbpService.unmarshallStringFromApi(fullXML);
        dBservice.saveExchangeRates(exchangeRatesRequest);
        return Optional.ofNullable(exchangeRatesMarshaller.exchangeRatesMarshaller(exchangeRatesRequest));
    }


    private ExchangeRatesSeries prepareExchangeRatesFromDB(RequestHolder requestHolder) {
        ExchangeRatesRequest exchangeRatesRequest = dBservice.findExchangeRates(requestHolder);
        return exchangeRatesMarshaller.exchangeRatesMarshaller(exchangeRatesRequest);
    }
}
