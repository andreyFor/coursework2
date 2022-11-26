package exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuestionNotExistException extends RuntimeException {
    public QuestionNotExistException() {
    }

    public QuestionNotExistException(String message) {
        super(message);
    }

    public QuestionNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuestionNotExistException(Throwable cause) {
        super(cause);
    }

    public QuestionNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}