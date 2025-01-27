package Project.BusTicketBooking.Application;

import Project.BusTicketBooking.Application.Model.Booking;
import Project.BusTicketBooking.Application.Model.Passengers;
import Project.BusTicketBooking.Application.Repository.BookingRepo;
import Project.BusTicketBooking.Application.Service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookingServicesTest {

    @Mock
    private BookingRepo bookingRepo;

    @InjectMocks
    private BookingService bookingService;

    private Booking mockBooking;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockBooking = new Booking();
        mockBooking.setBookingId(1);
    }

    @Test
    void testSaveBooking() {
        when(bookingRepo.existsById(mockBooking.getBookingId())).thenReturn(false);
        when(bookingRepo.save(mockBooking)).thenReturn(mockBooking);

        Booking savedBooking = bookingService.saveBooking(mockBooking);

        assertNotNull(savedBooking);
        assertEquals(1, savedBooking.getBookingId());
        verify(bookingRepo, times(1)).save(mockBooking);
    }

    @Test
    void testGetByBookingId() {
        when(bookingRepo.findByBookingId(1)).thenReturn(Optional.of(mockBooking));

        Booking booking = bookingService.getByBookingId(1);

        assertNotNull(booking);
        assertEquals(1, booking.getBookingId());
    }

    @Test
    void testGetBookingByPassenger() {
        Passengers mockPassenger = new Passengers();
        when(bookingRepo.findByPassengers(mockPassenger)).thenReturn(Collections.singletonList(mockBooking));

        List<Booking> bookings = bookingService.getBookingByPassenger(mockPassenger);

        assertNotNull(bookings);
        assertFalse(bookings.isEmpty());
    }
}
