package pl.com.khryniewicki.util;

import pl.com.khryniewicki.dto.request.ExchangeRatesEntity;
import pl.com.khryniewicki.dto.request.RequestHolder;
import pl.com.khryniewicki.dto.response.GetCurrencyRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RequestUtil {

    public static RequestHolder parseRequest(GetCurrencyRequest request) {
        ExchangeRatesEntity exchangeRatesEntity = new ExchangeRatesEntity();
        exchangeRatesEntity.setCurrency(request.getCurrency());
        LocalDate[] localDates = parseDates(request);
        return new RequestHolder(exchangeRatesEntity, localDates[0], localDates[1]);
    }

    private static LocalDate[] parseDates(GetCurrencyRequest request) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(request.getStartingDate(), formatter);
        LocalDate end = LocalDate.parse(request.getEndingDate(), formatter);
        LocalDate[] localDates = {start, end};

        return localDates;
    }
}
