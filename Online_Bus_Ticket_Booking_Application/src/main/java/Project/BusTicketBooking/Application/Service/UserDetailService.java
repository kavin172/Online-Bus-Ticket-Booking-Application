package Project.BusTicketBooking.Application.Service;


import Project.BusTicketBooking.Application.ExceptionHandler.UserNotFoundException;
import Project.BusTicketBooking.Application.Model.Passengers;
import Project.BusTicketBooking.Application.Repository.PassengerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private PassengerRepo passengerRepo;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Passengers passengers = passengerRepo.findByEmail(username);
        if (passengers == null) {
            throw new UserNotFoundException("User with email " + username + " not found");
        }
        return new UserDetails(passengers);
    }
}
