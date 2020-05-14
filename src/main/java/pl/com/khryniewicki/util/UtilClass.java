package pl.com.khryniewicki.util;

import pl.com.khryniewicki.request.CodeRequest;

import java.util.HashMap;

public class UtilClass {
    public static HashMap<String, CodeRequest> MapWithCurrencies() {
        HashMap<String, CodeRequest> mapWithCurrencies = new HashMap<>();
        for (CodeRequest currency : CodeRequest.values()) {
            mapWithCurrencies.put(currency.getCurrencyFullName(), currency);
        }
        return mapWithCurrencies;
    }
}
