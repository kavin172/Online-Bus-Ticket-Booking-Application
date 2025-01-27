package Project.BusTicketBooking.Application;

import Project.BusTicketBooking.Application.Model.Booking;
import Project.BusTicketBooking.Application.Model.Bus;
import Project.BusTicketBooking.Application.Model.Passengers;
import Project.BusTicketBooking.Application.Repository.BookingRepo;
import Project.BusTicketBooking.Application.Service.BookingService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookingServiceTest {

    @Mock
    private BookingRepo bookingRepo;

    @InjectMocks
    private BookingService bookingService;

    @Test
    public void testSaveBooking() {
        Passengers passenger = new Passengers("santhosh", "santhosh@example.com", "admin123", "USER");
        Bus bus = new Bus(1, "Bus Name", "From", "To", 100.0, LocalDate.now(), "10:00", "12:00");
        Booking booking = new Booking();
        booking.setPassengers(passenger);
        booking.setBusEntity(bus);
        booking.setNoOfPassengers(1);
        booking.setPaymentMode("Cash");

        when(bookingRepo.save(any(Booking.class))).thenReturn(booking);

        Booking savedBooking = bookingService.saveBooking(booking);

        verify(bookingRepo, times(1)).save(any(Booking.class));
        assertEquals(passenger, savedBooking.getPassengers());
        assertEquals(bus, savedBooking.getBusEntity());
        assertEquals(1, savedBooking.getNoOfPassengers());
        assertEquals("Cash", savedBooking.getPaymentMode());
    }
}

