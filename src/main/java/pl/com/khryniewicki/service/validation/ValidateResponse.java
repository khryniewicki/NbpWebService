package pl.com.khryniewicki.service.validation;

import org.springframework.stereotype.Service;
import pl.com.khryniewicki.dto.response.ExchangeRatesSeries;
import pl.com.khryniewicki.dto.response.GetCurrencyResponse;

import java.util.Objects;

@Service
public class ValidateResponse {

    public boolean validateResponse(GetCurrencyResponse response, ExchangeRatesSeries exchangeObject) {
        if (Objects.isNull(exchangeObject)) {
            response.setMessage("Api does not provide information about this currency");
            return true;
        }
        return false;
    }
}
