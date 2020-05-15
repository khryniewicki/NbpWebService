package pl.com.khryniewicki.dto.request;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class RateEntity {
    @Id
    @GeneratedValue()
    protected Long id;
    @ManyToOne
    protected ExchangeRatesEntity exchange;
    protected String no;
    protected LocalDate effectiveDate;
    protected float bid;
    protected float ask;
}
