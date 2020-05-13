package pl.com.khryniewicki.service;


import org.springframework.stereotype.Service;
import pl.com.khryniewicki.request.CodeRequest;
import pl.com.khryniewicki.request.ExchangeRatesRequest;
import pl.com.khryniewicki.request.RateRequest;
import pl.com.khryniewicki.response.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

@Service
public class ApiNbpService {

    public ExchangeRatesSeries getXMLFromApi(GetCurrencyRequest request) {
        String currencyFullName = request.getCurrency();
        String startingDate = request.getStartingDate();
        String endingDate = request.getEndingDate();

        CodeRequest code = getCurrencyCodeUsingCurrencyName(currencyFullName);
        String fullXML = parseXmlFromNBPApiToString(code, startingDate, endingDate);
        ExchangeRatesRequest exchangeRequestObject = parseStringToExchangeRateRequest(fullXML);

        return convertExchangeRequestObjectToExchangeResponseObject(exchangeRequestObject);

    }


    private CodeRequest getCurrencyCodeUsingCurrencyName(String currencyFullName) {
        return getMapWithCurrencies().get(currencyFullName);
    }

    private HashMap<String, CodeRequest> getMapWithCurrencies() {
        HashMap<String, CodeRequest> mapWithCurrencies = new HashMap<>();
        for (CodeRequest currency : CodeRequest.values()) {
            mapWithCurrencies.put(currency.getCurrencyFullName(), currency);
        }
        return mapWithCurrencies;
    }

    private String parseXmlFromNBPApiToString(CodeRequest code, String startingDate, String endingDate) {
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

    private ExchangeRatesRequest parseStringToExchangeRateRequest(String fulltext) {
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

    private ExchangeRatesSeries convertExchangeRequestObjectToExchangeResponseObject(ExchangeRatesRequest exchangeObject) {
        ArrayList<RateRequest> rateRequests = exchangeObject.getRateRequests();

        RateRequest rateWithMaxBid = getRateRequestWithMaxBid(rateRequests);
        RateRequest rateWithMinAsk = getRateRequestWithMinAsk(rateRequests);

        BestBidRate bestBidRate = BestBidRateAdapter(rateWithMaxBid);
        BestAskRate bestAskRate = BestAskRateAdapter(rateWithMinAsk);

       return ExchangeRatesSeriesAdapter(exchangeObject,bestBidRate,bestAskRate);
    }

    private ExchangeRatesSeries ExchangeRatesSeriesAdapter(ExchangeRatesRequest exchangeObject, BestBidRate bestBidRate, BestAskRate bestAskRate) {
        ExchangeRatesSeries exchange = new ExchangeRatesSeries();
        Rates rates = new Rates();

        exchange.setCurrency(exchangeObject.getCurrency());
        exchange.setCode(exchangeObject.getCode());
        exchange.setTable(exchangeObject.getTable());

        rates.setBestBidRate(bestBidRate);
        rates.setBestAskRate(bestAskRate);
        exchange.setRates(rates);

        return exchange;
    }


    private RateRequest getRateRequestWithMinAsk(ArrayList<RateRequest> rateRequests) {
        return rateRequests.stream()
                .min(Comparator.comparing(rate -> rate.getAsk()))
                .get();
    }
    private RateRequest getRateRequestWithMaxBid(ArrayList<RateRequest> rateRequests) {
        return rateRequests.stream()
                .max(Comparator.comparing(rate -> rate.getBid()))
                .get();
    }

    private BestAskRate BestAskRateAdapter(RateRequest rateWithMinAsk) {
        BestAskRate bestAskRate = new BestAskRate();
        bestAskRate.setAsk(rateWithMinAsk.getAsk());
        bestAskRate.setEffectiveDate(rateWithMinAsk.getEffectiveDate());
        bestAskRate.setNo(rateWithMinAsk.getNo());
        return bestAskRate;
    }
    private BestBidRate BestBidRateAdapter(RateRequest rateWithMaxBid) {
        BestBidRate bestBidRate = new BestBidRate();
        bestBidRate.setBid(rateWithMaxBid.getAsk());
        bestBidRate.setEffectiveDate(rateWithMaxBid.getEffectiveDate());
        bestBidRate.setNo(rateWithMaxBid.getNo());
        return bestBidRate;
    }
}
