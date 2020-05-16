package pl.com.khryniewicki.util;

import pl.com.khryniewicki.dto.request.RequestHolder;
import pl.com.khryniewicki.dto.response.GetCurrencyRequest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;

public class RequestUtil {

    public static RequestHolder parseRequest(GetCurrencyRequest request) {
        GregorianCalendar[] calendar = parseDates(request);
        return new RequestHolder(request.getCurrency(), calendar[0], calendar[1]);
    }

    private static GregorianCalendar[] parseDates(GetCurrencyRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate start = LocalDate.parse(request.getStartingDate(), formatter);
        LocalDate end = LocalDate.parse(request.getEndingDate(), formatter);

        GregorianCalendar st = GregorianCalendar.from(start.atStartOfDay(ZoneId.systemDefault()));
        GregorianCalendar en = GregorianCalendar.from(end.atStartOfDay(ZoneId.systemDefault()));

        GregorianCalendar[] calendar = new GregorianCalendar[]{st, en};
        return calendar;
    }
}
