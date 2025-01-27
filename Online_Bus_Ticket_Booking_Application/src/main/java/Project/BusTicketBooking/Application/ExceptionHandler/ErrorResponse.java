package Project.BusTicketBooking.Application.ExceptionHandler;

public class ErrorResponse {
    private int statusCode;
    private String message;
    private String uri;

    public ErrorResponse(int statusCode, String message, String uri) {
        this.statusCode = statusCode;
        this.message = message;
        this.uri = uri;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
