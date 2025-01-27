package Project.BusTicketBooking.Application.ExceptionHandler;

public class UnauthorizedAccessException extends RuntimeException {
    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
