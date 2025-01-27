package Project.BusTicketBooking.Application.ExceptionHandler;

public class UserDetailsException extends RuntimeException {

    public UserDetailsException(String message) {
        super(message);
    }

    public UserDetailsException(String message, Throwable cause) {
        super(message, cause);
    }
}
