package Project.BusTicketBooking.Application.ExceptionHandler;

public class AuthenticationFailureException extends RuntimeException {
    public AuthenticationFailureException(String message) {
        super(message);
    }
}
