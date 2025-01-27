package Project.BusTicketBooking.Application.Repository;


import Project.BusTicketBooking.Application.Model.Passengers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerRepo extends JpaRepository<Passengers,Long> {

    //Method to find passengers by Mail
    Passengers findByEmail(String email);

    //Method to find passenger by their PassengerId
    Optional<Passengers> getByPassengerId(Long passengerId);

}
