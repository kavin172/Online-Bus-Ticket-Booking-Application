package Project.BusTicketBooking.Application;

import Project.BusTicketBooking.Application.ExceptionHandler.UserDetailsException;
import Project.BusTicketBooking.Application.Model.Passengers;
import Project.BusTicketBooking.Application.Service.UserDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserDetailsTest {

    private Passengers mockPassenger;

    @BeforeEach
    public void setUp() {
        mockPassenger = mock(Passengers.class);
    }

    @Test
    public void testConstructor_Success() {
        // Arrange
        String email = "test@example.com";
        String password = "password123";
        String role = "USER";

        when(mockPassenger.getEmail()).thenReturn(email);
        when(mockPassenger.getPassword()).thenReturn(password);
        when(mockPassenger.getRole()).thenReturn(role);

        // Act
        UserDetails userDetails = new UserDetails(mockPassenger);

        // Assert
        assertNotNull(userDetails, "UserDetails object should be created");
        assertEquals(email, userDetails.getUsername(), "Username should match email");
        assertEquals(password, userDetails.getPassword(), "Password should match");
    }

    @Test
    public void testConstructor_PassengerNull() {
        // Act & Assert
        assertThrows(UserDetailsException.class, () -> new UserDetails(null), "Should throw exception when passenger is null");
    }

    @Test
    public void testConstructor_EmailNull() {
        // Arrange
        when(mockPassenger.getEmail()).thenReturn(null);
        when(mockPassenger.getRole()).thenReturn("USER");

        // Act & Assert
        assertThrows(UserDetailsException.class, () -> new UserDetails(mockPassenger), "Should throw exception when email is null");
    }

    @Test
    public void testConstructor_EmailEmpty() {
        // Arrange
        when(mockPassenger.getEmail()).thenReturn("");
        when(mockPassenger.getRole()).thenReturn("USER");

        // Act & Assert
        assertThrows(UserDetailsException.class, () -> new UserDetails(mockPassenger), "Should throw exception when email is empty");
    }

    @Test
    public void testConstructor_RoleNull() {
        // Arrange
        when(mockPassenger.getEmail()).thenReturn("test@example.com");
        when(mockPassenger.getRole()).thenReturn(null);

        // Act & Assert
        assertThrows(UserDetailsException.class, () -> new UserDetails(mockPassenger), "Should throw exception when role is null");
    }

    @Test
    public void testConstructor_RoleEmpty() {
        // Arrange
        when(mockPassenger.getEmail()).thenReturn("test@example.com");
        when(mockPassenger.getRole()).thenReturn("");

        // Act & Assert
        assertThrows(UserDetailsException.class, () -> new UserDetails(mockPassenger), "Should throw exception when role is empty");
    }

    @Test
    public void testGetAuthorities_Success() {
        // Arrange
        String email = "test@example.com";
        String password = "password123";
        String role = "USER";

        when(mockPassenger.getEmail()).thenReturn(email);
        when(mockPassenger.getPassword()).thenReturn(password);
        when(mockPassenger.getRole()).thenReturn(role);

        UserDetails userDetails = new UserDetails(mockPassenger);

        // Act
        var authorities = userDetails.getAuthorities();

        // Assert
        assertNotNull(authorities, "Authorities should not be null");
        assertEquals(1, authorities.size(), "There should be one authority");
        assertEquals(role, authorities.iterator().next().getAuthority(), "Authority should match the passenger's role");
    }

    @Test
    public void testGetAuthorities_RoleMissing() {
        // Arrange
        when(mockPassenger.getEmail()).thenReturn("test@example.com");
        when(mockPassenger.getPassword()).thenReturn("password123");
        when(mockPassenger.getRole()).thenReturn(null);

        UserDetails userDetails = new UserDetails(mockPassenger);

        // Act & Assert
        assertThrows(UserDetailsException.class, userDetails::getAuthorities, "Should throw exception when role is missing");
    }

    @Test
    public void testIsAccountNonExpired() {
        // Arrange
        String email = "test@example.com";
        String password = "password123";
        String role = "USER";

        when(mockPassenger.getEmail()).thenReturn(email);
        when(mockPassenger.getPassword()).thenReturn(password);
        when(mockPassenger.getRole()).thenReturn(role);

        UserDetails userDetails = new UserDetails(mockPassenger);

        // Act & Assert
        assertTrue(userDetails.isAccountNonExpired(), "Account should not be expired");
    }

    @Test
    public void testIsAccountNonLocked() {
        // Arrange
        String email = "test@example.com";
        String password = "password123";
        String role = "USER";

        when(mockPassenger.getEmail()).thenReturn(email);
        when(mockPassenger.getPassword()).thenReturn(password);
        when(mockPassenger.getRole()).thenReturn(role);

        UserDetails userDetails = new UserDetails(mockPassenger);

        // Act & Assert
        assertTrue(userDetails.isAccountNonLocked(), "Account should not be locked");
    }

    @Test
    public void testIsCredentialsNonExpired() {
        // Arrange
        String email = "test@example.com";
        String password = "password123";
        String role = "USER";

        when(mockPassenger.getEmail()).thenReturn(email);
        when(mockPassenger.getPassword()).thenReturn(password);
        when(mockPassenger.getRole()).thenReturn(role);

        UserDetails userDetails = new UserDetails(mockPassenger);

        // Act & Assert
        assertTrue(userDetails.isCredentialsNonExpired(), "Credentials should not be expired");
    }

    @Test
    public void testIsEnabled() {
        // Arrange
        String email = "test@example.com";
        String password = "password123";
        String role = "USER";

        when(mockPassenger.getEmail()).thenReturn(email);
        when(mockPassenger.getPassword()).thenReturn(password);
        when(mockPassenger.getRole()).thenReturn(role);

        UserDetails userDetails = new UserDetails(mockPassenger);

        // Act & Assert
        assertTrue(userDetails.isEnabled(), "User should be enabled");
    }
}
