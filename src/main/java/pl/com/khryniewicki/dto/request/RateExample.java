package pl.com.khryniewicki.dto.request;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Data
public class RateExample {
    @Id
    @GeneratedValue()
    protected Long id;
    @ManyToOne
    protected Currency currency;
    protected String no;
    protected String effectiveDate;
    protected float bid;
    protected float ask;
}
