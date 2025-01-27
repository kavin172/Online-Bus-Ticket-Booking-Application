package Project.BusTicketBooking.Application.ExceptionHandler;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(String message) {
        super(message);
    }
}
