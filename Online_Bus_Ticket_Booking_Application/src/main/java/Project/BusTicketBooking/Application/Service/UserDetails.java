package Project.BusTicketBooking.Application.Service;

import Project.BusTicketBooking.Application.ExceptionHandler.UserDetailsException;
import Project.BusTicketBooking.Application.Model.Passengers;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Getter
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    private Passengers passengers;

    // Constructor
    public UserDetails(Passengers passengers) {
        if (passengers == null) {
            throw new UserDetailsException("Passenger object cannot be null");
        }
        if (passengers.getEmail() == null || passengers.getEmail().isEmpty()) {
            throw new UserDetailsException("Passenger email cannot be null or empty");
        }
        if (passengers.getRole() == null || passengers.getRole().isEmpty()) {
            throw new UserDetailsException("Passenger role cannot be null or empty");
        }
        this.passengers = passengers;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (passengers.getRole() == null || passengers.getRole().isEmpty()) {
            throw new UserDetailsException("Role is missing for the passenger");
        }
        return Collections.singletonList(new SimpleGrantedAuthority(passengers.getRole()));
    }

    @Override
    public String getPassword() {
        return passengers.getPassword();
    }

    @Override
    public String getUsername() {
        return passengers.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
