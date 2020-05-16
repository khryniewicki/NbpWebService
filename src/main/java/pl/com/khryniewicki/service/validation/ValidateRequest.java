package pl.com.khryniewicki.service.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import pl.com.khryniewicki.dto.response.GetCurrencyRequest;
import pl.com.khryniewicki.dto.response.GetCurrencyResponse;
import pl.com.khryniewicki.util.CurrencyUtil;

import java.time.Duration;
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
        } else if (!isDatesRangeCorrect(request)) {
            message = ("Invalid dates range");
            isInvalid = true;
        } else if (!isCurrencyNameValid(request)) {
            message = ("Invalid currency name");
            isInvalid = true;
        }

        if (isInvalid) response.setMessage(message);

        return isInvalid;
    }

    private boolean isDatesRangeCorrect(GetCurrencyRequest request) {
        String startingDate = request.getStartingDate();
        String endingDate = request.getEndingDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startingDate, formatter);
        LocalDate end = LocalDate.parse(endingDate, formatter);

        Duration diff = Duration.between(start.atStartOfDay(), end.atStartOfDay());
        return diff.toDays() < 367;
    }

    private boolean isCurrencyNameValid(GetCurrencyRequest request) {
        String currencyFullName = request.getCurrency();
        return CurrencyUtil.getCurrenciesCode().containsKey(currencyFullName);
    }

    private boolean isDateValid(GetCurrencyRequest request) {
        boolean isDateValid;

        String startingDate = request.getStartingDate();
        String endingDate = request.getEndingDate();

        isDateValid = isRequestDateValid(startingDate) && isRequestDateValid(endingDate);
        if (!isDateValid) return false;

        isDateValid = isDateBefore(startingDate, endingDate);
        return isDateValid;

    }

    private boolean isDateBefore(String startingDate, String endingDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startingDate, formatter);
        LocalDate end = LocalDate.parse(endingDate, formatter);
        LocalDate obsolateDate = LocalDate.of(2001, 1, 31);

        if (start.isAfter(end)) {
            return false;
        } else if (start.isAfter(LocalDate.now())) {
            return false;
        } else if (end.isAfter(LocalDate.now())) {
            return false;
        } else if (start.isBefore(obsolateDate)) {
            return false;
        } else {
            return !end.isBefore(obsolateDate);
        }
    }

    private boolean isRequestDateValid(String date) {
        String regex = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);

        return matcher.matches();
    }
}
