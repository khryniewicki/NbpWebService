package pl.com.khryniewicki.dto.request;

import lombok.Data;
import pl.com.khryniewicki.dto.response.Code;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class ExchangeRatesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String currency;
    protected Code code;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "currency")
    protected List<RateEntity> rateEntities;
}
