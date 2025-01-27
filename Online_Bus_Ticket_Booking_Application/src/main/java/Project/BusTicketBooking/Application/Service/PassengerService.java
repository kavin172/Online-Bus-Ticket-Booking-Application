package Project.BusTicketBooking.Application.Service;

import Project.BusTicketBooking.Application.DTO.UserDto;
import Project.BusTicketBooking.Application.ExceptionHandler.InvalidUserInputException;
import Project.BusTicketBooking.Application.ExceptionHandler.PassengerNotFoundException;
import Project.BusTicketBooking.Application.Model.Passengers;
import Project.BusTicketBooking.Application.Repository.PassengerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PassengerService implements UserService {

    @Autowired
    private PassengerRepo passengerRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Passengers save(UserDto userDto) {
        if (userDto.getName() == null || userDto.getEmail() == null || userDto.getPassword() == null) {
            throw new InvalidUserInputException("Name, Email, and Password are required fields.");
        }

        Passengers user = new Passengers();
        user.setFullName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(userDto.getRole());
        return passengerRepo.save(user);
    }

    public Passengers getCurrentLoggedInPassenger() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            Passengers passenger = passengerRepo.findByEmail(username);
            if (passenger == null) {
                throw new PassengerNotFoundException("No passenger found with email: " + username);
            }
            return passenger;
        } else {
            throw new PassengerNotFoundException("Unable to find the currently logged-in passenger.");
        }
    }

    public Passengers getById(long passengerId) {
        return passengerRepo.getByPassengerId(passengerId)
                .orElseThrow(() -> new PassengerNotFoundException("Passenger not found with ID: " + passengerId));
    }
}
