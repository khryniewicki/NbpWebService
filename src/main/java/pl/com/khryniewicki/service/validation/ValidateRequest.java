package pl.com.khryniewicki.service.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import pl.com.khryniewicki.dto.response.GetCurrencyRequest;
import pl.com.khryniewicki.dto.response.GetCurrencyResponse;
import pl.com.khryniewicki.util.CurrencyUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ValidateRequest {

    public boolean validateRequest(@RequestPayload GetCurrencyRequest request, GetCurrencyResponse response) {
        boolean isInvalid = false;
        String message = "";

        if (Objects.isNull(request.getCurrency())) {
            message = ("No currency name found");
            isInvalid = true;
        } else if (Objects.isNull(request.getStartingDate()) || Objects.isNull(request.getEndingDate())) {
            message = ("Date not found");
            isInvalid = true;
        } else if (!isDateValid(request)) {
            message = ("Invalid date");
            isInvalid = true;
        } else if (!isCurrencyNameValid(request)) {
            message = ("Invalid currency name");
            isInvalid = true;
        }

        if (isInvalid) response.setMessage(message);

        return isInvalid;
    }

    private boolean isCurrencyNameValid(GetCurrencyRequest request) {
        String currencyFullName = request.getCurrency();
        return CurrencyUtil.getCurrenciesCode().containsKey(currencyFullName);
    }

    private boolean isDateValid(GetCurrencyRequest request) {
        boolean isDateValid;

        String startingDate = request.getStartingDate();
        String endingDate = request.getEndingDate();

        isDateValid = (isRequestDateValid(startingDate) && isRequestDateValid(endingDate)) ? true : false;
        if (!isDateValid) return false;

        isDateValid = isStartingDateBeforeEndingDate(startingDate, endingDate);
        return isDateValid;

    }

    private boolean isStartingDateBeforeEndingDate(String startingDate, String endingDate) {
        boolean isDateValid;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );
        LocalDate start = LocalDate.parse( startingDate , formatter );
        LocalDate end = LocalDate.parse( endingDate , formatter );
        isDateValid=start.isBefore( end );
        return isDateValid;
    }

    private boolean isRequestDateValid(String date) {
        String regex = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        boolean valid = matcher.matches();

        return valid;
    }
}