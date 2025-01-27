package Project.BusTicketBooking.Application.ExceptionHandler;

public class InvalidUserInputException extends RuntimeException {

    public InvalidUserInputException(String message) {
        super(message);
    }
}
