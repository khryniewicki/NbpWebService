//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.05.16 at 05:29:49 AM CEST 
//


package pl.com.khryniewicki.dto.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Message" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ExchangeRatesSeries" type="{http://response.dto.khryniewicki.com.pl}ExchangeRatesSeries"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "message",
    "exchangeRatesSeries"
})
@XmlRootElement(name = "getCurrencyResponse")
public class GetCurrencyResponse {

    @XmlElement(name = "Message", required = true)
    protected String message;
    @XmlElement(name = "ExchangeRatesSeries", required = true)
    protected ExchangeRatesSeries exchangeRatesSeries;

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the exchangeRatesSeries property.
     * 
     * @return
     *     possible object is
     *     {@link ExchangeRatesSeries }
     *     
     */
    public ExchangeRatesSeries getExchangeRatesSeries() {
        return exchangeRatesSeries;
    }

    /**
     * Sets the value of the exchangeRatesSeries property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExchangeRatesSeries }
     *     
     */
    public void setExchangeRatesSeries(ExchangeRatesSeries value) {
        this.exchangeRatesSeries = value;
    }

}
