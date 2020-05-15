package pl.com.khryniewicki.dto.request;

import lombok.Data;

import java.time.LocalDate;
@Data
public class RequestHolder {
    private ExchangeRatesEntity exchangeRatesEntity;
    private LocalDate startingDate;
    private LocalDate endingDate;

    public RequestHolder(ExchangeRatesEntity exchangeRatesEntity, LocalDate startingDate, LocalDate endingDate) {
        this.exchangeRatesEntity = exchangeRatesEntity;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public RequestHolder() {
    }
}
