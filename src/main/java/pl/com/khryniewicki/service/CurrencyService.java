package pl.com.khryniewicki.service;

import org.springframework.stereotype.Service;
import pl.com.khryniewicki.request.CodeRequest;
import pl.com.khryniewicki.request.ExchangeRatesRequest;
import pl.com.khryniewicki.util.UtilClass;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

@Service
public class CurrencyService {

    protected ExchangeRatesRequest parseStringToExchangeRateRequest(String fulltext) {
        JAXBContext jaxbContext;
        ExchangeRatesRequest unmarshal = new ExchangeRatesRequest();
        try {
            jaxbContext = JAXBContext.newInstance(ExchangeRatesRequest.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            unmarshal = (ExchangeRatesRequest) jaxbUnmarshaller.unmarshal(new StringReader(fulltext));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return unmarshal;
    }
    protected String parseXmlFromNBPApiToString(String  currencyFullName, String startingDate, String endingDate) {
        CodeRequest code = getCurrencyCodeUsingCurrencyName(currencyFullName);

        String path = "https://api.nbp.pl/api/exchangerates/rates/c/" + code.name().toLowerCase() + "/" + startingDate + "/" + endingDate + "/?format=xml";
        String fulltext = "";
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new URL(path).openStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                fulltext += inputLine;
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return fulltext;
    }


    private CodeRequest getCurrencyCodeUsingCurrencyName(String currencyFullName) {
        return UtilClass.MapWithCurrencies().get(currencyFullName);
    }


}
