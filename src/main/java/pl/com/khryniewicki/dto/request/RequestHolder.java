package pl.com.khryniewicki.dto.request;

import lombok.Data;

import java.util.GregorianCalendar;

@Data
public class RequestHolder {
    private String currency;
    private GregorianCalendar startingDate;
    private GregorianCalendar endingDate;

    public RequestHolder(String currency, GregorianCalendar startingDate, GregorianCalendar endingDate) {
        this.currency = currency;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public RequestHolder() {
    }
}
