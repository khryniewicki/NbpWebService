package pl.com.khryniewicki.service.responseService.nbpservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.khryniewicki.dto.request.CodeRequest;
import pl.com.khryniewicki.dto.request.ExchangeRatesRequest;
import pl.com.khryniewicki.dto.request.RateRequest;
import pl.com.khryniewicki.service.requestService.ExchangeRatesService;
import pl.com.khryniewicki.service.requestService.RateRequestService;
import pl.com.khryniewicki.service.responseService.dbService.DBservice;
import pl.com.khryniewicki.util.CurrencyUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NbpServiceImp implements NbpService {



@Override
    public ExchangeRatesRequest unmarshallStringFromApi(String fulltext) {
        JAXBContext jaxbContext;
        ExchangeRatesRequest unmarshal = new ExchangeRatesRequest();

        try {
            jaxbContext = JAXBContext.newInstance(ExchangeRatesRequest.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            if (!fulltext.isEmpty()) {
                unmarshal = (ExchangeRatesRequest) jaxbUnmarshaller.unmarshal(new StringReader(fulltext));
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }


        return unmarshal;
    }




    @Override
    public String parseXmlFromNBPApiToString(String currencyFullName, String startingDate, String endingDate) {
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
        } catch (NullPointerException e) {
            String message="Null Pointer";
        }
        return fulltext;
    }

    @Override
    public CodeRequest getCurrencyCodeUsingCurrencyName(String currencyFullName) {
        return CurrencyUtil.getCurrencyCode(currencyFullName);
    }


}
