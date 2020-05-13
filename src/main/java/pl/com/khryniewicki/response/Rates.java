//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.05.13 at 06:20:35 PM CEST 
//


package pl.com.khryniewicki.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Rates complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Rates"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="BestBid" type="{http://response.khryniewicki.com.pl}RateBid"/&gt;
 *         &lt;element name="BestAsk" type="{http://response.khryniewicki.com.pl}RateAsk"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Rates", propOrder = {
    "bestBid",
    "bestAsk"
})
public class Rates {

    @XmlElement(name = "BestBid", required = true)
    protected RateBid bestBid;
    @XmlElement(name = "BestAsk", required = true)
    protected RateAsk bestAsk;

    /**
     * Gets the value of the bestBid property.
     * 
     * @return
     *     possible object is
     *     {@link RateBid }
     *     
     */
    public RateBid getBestBid() {
        return bestBid;
    }

    /**
     * Sets the value of the bestBid property.
     * 
     * @param value
     *     allowed object is
     *     {@link RateBid }
     *     
     */
    public void setBestBid(RateBid value) {
        this.bestBid = value;
    }

    /**
     * Gets the value of the bestAsk property.
     * 
     * @return
     *     possible object is
     *     {@link RateAsk }
     *     
     */
    public RateAsk getBestAsk() {
        return bestAsk;
    }

    /**
     * Sets the value of the bestAsk property.
     * 
     * @param value
     *     allowed object is
     *     {@link RateAsk }
     *     
     */
    public void setBestAsk(RateAsk value) {
        this.bestAsk = value;
    }

}
