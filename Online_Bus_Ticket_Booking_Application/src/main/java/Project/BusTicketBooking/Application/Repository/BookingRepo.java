package Project.BusTicketBooking.Application.Repository;

import Project.BusTicketBooking.Application.Model.Booking;
import Project.BusTicketBooking.Application.Model.PassengerDetail;
import Project.BusTicketBooking.Application.Model.Passengers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepo extends JpaRepository<Booking,Integer> {
    //Method to findByBookingId
    Optional<Booking> findByBookingId(int bookingId);

    //Method to findByPassenger
    List<Booking> findByPassengers(Passengers passengers);

    //Method to get travelPassengers Details
    List<Booking> findByPassengerDetails(PassengerDetail passengerDetail);


}

