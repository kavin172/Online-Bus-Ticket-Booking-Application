package Project.BusTicketBooking.Application.ExceptionHandler;

public class BusNotFoundException extends RuntimeException {
    public BusNotFoundException(String message) {
        super(message);
    }
}
