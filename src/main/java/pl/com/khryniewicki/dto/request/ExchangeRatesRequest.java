//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.05.12 at 08:43:38 PM CEST 
//


package pl.com.khryniewicki.dto.request;


import pl.com.khryniewicki.dto.response.Code;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.List;


/**
 * <p>Java class for ExchangeRatesSeries complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ExchangeRatesSeries"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Table" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Currency" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Code" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Rates" type="{http://www.w3.org/2001/XMLSchema-instance}Rate"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExchangeRatesRequest", propOrder = {
        "id",
        "tableName",
        "currency",
        "code",
        "rateRequests"
})
@XmlRootElement(name = "ExchangeRatesSeries")
@Entity

public class ExchangeRatesRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement(name = "id", required = true)

    protected Long id;
    @XmlElement(name = "TableName", required = true)
    protected String tableName;
    @Column(unique = true)
    @XmlElement(name = "Currency", required = true)
    protected String currency;
    @Column(unique = true)
    @XmlElement(name = "Code", required = true)
    protected Code code;
    @XmlElementWrapper(name = "Rates")
    @XmlElement(name = "Rate", required = true)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exchange")
    protected List<RateRequest> rateRequests;


    public List<RateRequest> getRateRequests() {
        return rateRequests;
    }

    public void setRateRequests(List<RateRequest> rateRequests) {
        this.rateRequests = rateRequests;
    }

    /**
     * Gets the value of the table property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTable() {
        return tableName;
    }

    /**
     * Sets the value of the table property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTable(String value) {
        this.tableName = value;
    }

    /**
     * Gets the value of the currency property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Gets the value of the code property.
     *
     * @return possible object is
     * {@link String }
     */
    public Code getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCode(Code value) {
        this.code = value;
    }

    /**
     * Gets the value of the rates property.
     *
     * @return possible object is
     * {@link RateRequest }
     */


//    /**
//     * Sets the value of the rates property.
//     *
//     * @param value
//     *     allowed object is
//     *     {@link Rate }
//     *
//     */
    @Override
    public String toString() {
        return "ExchangeRatesSeries{" +
                "table='" + tableName + '\'' +
                ", currency='" + currency + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
