package pl.com.khryniewicki.dto.request;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Data
public class RateExample {
    @Id
    @GeneratedValue()
    protected Long id;
//    @ManyToOne
    @Transient
    protected Currency currency;
    protected String no;
    protected String effectiveDate;
    protected float bid;
    protected float ask;
}
