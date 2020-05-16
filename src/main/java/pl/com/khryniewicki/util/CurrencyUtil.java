package pl.com.khryniewicki.util;


import pl.com.khryniewicki.dto.request.CodeRequest;

import java.util.HashMap;
import java.util.Objects;

public class CurrencyUtil {

    private static HashMap<String, CodeRequest> currenciesCode;

    public static HashMap<String, CodeRequest> getCurrenciesCode() {
        prepareMap();
        return currenciesCode;
    }

    public static CodeRequest getCurrencyCode(String currencyFullName) {
        return getCurrenciesCode().get(currencyFullName);
    }

    private static void prepareMap() {
        if (Objects.isNull(currenciesCode)) {
            currenciesCode = new HashMap<>();
            for (CodeRequest currency : CodeRequest.values()) {
                currenciesCode.put(currency.getCurrencyFullName(), currency);
            }
        }
    }
}
