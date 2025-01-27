package Project.BusTicketBooking.Application.ExceptionHandler;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String message) {
        super(message);
    }
}
