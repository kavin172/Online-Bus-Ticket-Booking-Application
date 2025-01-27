package Project.BusTicketBooking.Application.Service;


import Project.BusTicketBooking.Application.ExceptionHandler.BookingAlreadyExistsException;
import Project.BusTicketBooking.Application.ExceptionHandler.BookingNotFoundException;
import Project.BusTicketBooking.Application.Model.Booking;
import Project.BusTicketBooking.Application.Model.PassengerDetail;
import Project.BusTicketBooking.Application.Model.Passengers;
import Project.BusTicketBooking.Application.Repository.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    public Booking saveBooking(Booking booking) {
        // Check if booking already exists (example check)
        if (bookingRepo.existsById(booking.getBookingId())) {
            throw new BookingAlreadyExistsException("Booking already exists with this ID: " + booking.getBookingId());
        }
        return bookingRepo.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    public Booking getByBookingId(int bookingId) {
        return bookingRepo.findByBookingId(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + bookingId));
    }

    public List<Booking> getBookingByPassenger(Passengers passengers) {
        return bookingRepo.findByPassengers(passengers);
    }

    public Booking saveBookingWithPassengerDetails(Booking booking, List<PassengerDetail> passengerDetails) {
        for (PassengerDetail detail : passengerDetails) {
            detail.setBooking(booking);
        }
        booking.setPassengerDetails(passengerDetails);
        return bookingRepo.save(booking);
    }
}
