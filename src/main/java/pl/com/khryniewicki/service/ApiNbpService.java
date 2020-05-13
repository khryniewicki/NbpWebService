package pl.com.khryniewicki.service;


import org.springframework.stereotype.Service;
import pl.com.khryniewicki.response.Code;
import pl.com.khryniewicki.request.ExchangeRatesRequest;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.util.HashMap;

@Service
public class ApiNbpService {

    public ExchangeRatesRequest getXMLFromApi(String currencyFullName){
        Code code = getCurrencyCodeUsingCurrencyName(currencyFullName);
        String fullXML = parseXmlFromNBPApiToString(code);
        return parseStringToObjects(fullXML);
    }

    private  Code getCurrencyCodeUsingCurrencyName(String currencyFullName) {
        return getMapWithCurrencies().get(currencyFullName);
    }

    private  HashMap<String, Code> getMapWithCurrencies() {
        HashMap<String, Code> mapWithCurrencies = new HashMap<>();
        for (Code currency : Code.values()) {
            mapWithCurrencies.put(currency.getCurrencyFullName(), currency);
        }
        return mapWithCurrencies;
    }

    private  String parseXmlFromNBPApiToString(Code code) {
        String path = "https://api.nbp.pl/api/exchangerates/rates/c/" + code.name().toLowerCase() + "/2012-01-01/2012-01-31/?format=xml";
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

    private ExchangeRatesRequest parseStringToObjects(String fulltext) {
        JAXBContext jaxbContext;
        ExchangeRatesRequest unmarshal = new ExchangeRatesRequest();
        try {
            jaxbContext = JAXBContext.newInstance(ExchangeRatesRequest.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            unmarshal = (ExchangeRatesRequest) jaxbUnmarshaller.unmarshal(new StringReader(fulltext));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        System.out.println(unmarshal.toString());
        return unmarshal;
    }

}
