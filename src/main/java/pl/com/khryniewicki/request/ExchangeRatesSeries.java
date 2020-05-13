//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.05.12 at 08:43:38 PM CEST 
//


package pl.com.khryniewicki.request;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;


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
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExchangeRatesSeries", propOrder = {
    "table",
    "currency",
    "code",
    "rates"
})
@XmlRootElement(name="ExchangeRatesSeries")
public class ExchangeRatesSeries {

    @XmlElement(name = "Table", required = true)
    protected String table;
    @XmlElement(name = "Currency", required = true)
    protected String currency;
    @XmlElement(name = "Code", required = true)
    protected Code code;
    @XmlElementWrapper(name = "Rates")
    @XmlElement(name = "Rate")
    protected ArrayList<pl.com.khryniewicki.request.Rate> rates;

    public ArrayList<pl.com.khryniewicki.request.Rate> getRates() {
        return rates;
    }

    public void setRates(ArrayList<pl.com.khryniewicki.request.Rate> rates) {
        this.rates = rates;
    }

    /**
     * Gets the value of the table property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTable() {
        return table;
    }

    /**
     * Sets the value of the table property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTable(String value) {
        this.table = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Code getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(Code value) {
        this.code = value;
    }

    /**
     * Gets the value of the rates property.
     * 
     * @return
     *     possible object is
     *     {@link pl.com.khryniewicki.request.Rate }
     *     
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
                "table='" + table + '\'' +
                ", currency='" + currency + '\'' +
                ", code='" + code + '\'' +
                ", rates=" + rates +
                '}';
    }
}
