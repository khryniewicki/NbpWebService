package pl.com.khryniewicki.dto.request;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class RateEntity {
    @Id
    @GeneratedValue()
    protected Long id;
    @ManyToOne
    protected ExchangeRatesEntity currency;
    protected String no;
    protected String effectiveDate;
    protected float bid;
    protected float ask;
}
