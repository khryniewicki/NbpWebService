package pl.com.khryniewicki.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import pl.com.khryniewicki.request.CodeRequest;
import pl.com.khryniewicki.response.GetCurrencyRequest;
import pl.com.khryniewicki.response.GetCurrencyResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
@RequiredArgsConstructor
public class Validate {
    private final ApiNbpService apiNbpService;

    protected boolean validateRequest(@RequestPayload GetCurrencyRequest request, GetCurrencyResponse response) {
            String message="";
        if (!isDateValid(request)) {
            response.setMessage("Invalid dates");
            return true;
        }
        else if (!isCurrencyNameValid(request)){
            response.setMessage("Invalid currency name");
            return true;
        }
        return false;
    }
    private boolean isCurrencyNameValid(GetCurrencyRequest request) {
        String currencyFullName=request.getCurrency();
        HashMap<String, CodeRequest> mapWithCurrencies = apiNbpService.getMapWithCurrencies();
        return mapWithCurrencies.containsKey(currencyFullName);
    }

    private boolean isDateValid(GetCurrencyRequest request) {
        boolean isDateValid;

        String startingDate = request.getStartingDate();
        String endingDate = request.getEndingDate();

        isDateValid= (isRequestDateValid(startingDate) && isRequestDateValid(endingDate)) ? true :false;
        if(!isDateValid) return false;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "DD-MM-YYYY" );
        LocalDate start = LocalDate.parse( startingDate , formatter );
        LocalDate end = LocalDate.parse( startingDate , formatter );

        return start.isBefore( end );

    }

    private boolean isRequestDateValid(String date) {
        String regex = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        boolean valid = matcher.matches();

        return valid;
    }
}
