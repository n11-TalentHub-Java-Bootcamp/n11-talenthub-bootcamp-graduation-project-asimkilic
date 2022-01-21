package tr.gov.nvi.tckimlik.WS.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class KpsServiceUnavailableException extends RuntimeException {
    public KpsServiceUnavailableException(String message) {
        super(message);
    }

    public final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
}
