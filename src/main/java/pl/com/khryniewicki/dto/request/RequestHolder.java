package pl.com.khryniewicki.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.GregorianCalendar;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestHolder {
    private String currency;
    private GregorianCalendar startingDate;
    private GregorianCalendar endingDate;

}
