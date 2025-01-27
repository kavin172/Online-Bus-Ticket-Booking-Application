package Project.BusTicketBooking.Application.Repository;

import Project.BusTicketBooking.Application.Model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BusRepo extends JpaRepository<Bus,Integer>{

    Optional<Bus> findByBusId(int busID);
    Optional<Bus>findBySerialNo(int serialNo);
    List<Bus> findByFromAndToAndCheckingDate(String from, String to, LocalDate checkingDate);
}
