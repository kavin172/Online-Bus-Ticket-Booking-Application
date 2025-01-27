package Project.BusTicketBooking.Application.ExceptionHandler;

public class BookingAlreadyExistsException extends RuntimeException {
    public BookingAlreadyExistsException(String message) {
        super(message);
    }
}
