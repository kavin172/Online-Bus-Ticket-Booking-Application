package Project.BusTicketBooking.Application.Service;

import Project.BusTicketBooking.Application.ExceptionHandler.BusNotFoundException;
import Project.BusTicketBooking.Application.Model.Bus;
import Project.BusTicketBooking.Application.Repository.BusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    @Autowired
    private BusRepo busRepo;

    //Method to save Bus
    public Bus saveBus(Bus bus){
        return busRepo.save(bus);
    }

    //Method to display all buses
    public List<Bus> getAllBus(){
        return busRepo.findAll();
    }

    //Method to find Bus by ID
    public Bus getBusById(int busId){
        return busRepo.findByBusId(busId).orElseThrow(() -> new BusNotFoundException("Bus not found with ID: " + busId));
    }

    //Method to get Bus by ID (Optional)
    public Optional<Bus> getBusByIdOptional(int busId){
        return busRepo.findByBusId(busId);
    }

    //Method to update Bus
    public Bus updateBus(Bus busUpdate){
        Optional<Bus> existingBus = busRepo.findByBusId(busUpdate.getBusId());

        if (existingBus.isPresent()) {
            Bus currentBus = existingBus.get();
            currentBus.setBusId(busUpdate.getBusId());
            currentBus.setBusName(busUpdate.getBusName());
            currentBus.setFrom(busUpdate.getFrom());
            currentBus.setTo(busUpdate.getTo());
            currentBus.setTicketFar(busUpdate.getTicketFar());
            currentBus.setCheckingDate(busUpdate.getCheckingDate());
            currentBus.setDepartureTime(busUpdate.getDepartureTime());
            currentBus.setArrivalTime(busUpdate.getArrivalTime());

            return busRepo.save(currentBus);
        } else {
            throw new BusNotFoundException("Bus not found with ID: " + busUpdate.getBusId());
        }
    }

    //Method to delete Bus by serial number
    public void deleteBus(int serialNo) {
        Optional<Bus> bus = busRepo.findBySerialNo(serialNo);
        if (bus.isPresent()) {
            busRepo.delete(bus.get());
        } else {
            throw new BusNotFoundException("Bus not found with serial number: " + serialNo);
        }
    }

    //Method to filter buses by location and date for booking
    public List<Bus> findBuses(String from, String to, LocalDate checkingDate){
        return busRepo.findByFromAndToAndCheckingDate(from, to, checkingDate);
    }
}
