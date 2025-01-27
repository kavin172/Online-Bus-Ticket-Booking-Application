package Project.BusTicketBooking.Application;

import Project.BusTicketBooking.Application.Model.Passengers;
import Project.BusTicketBooking.Application.Repository.PassengerRepo;
import Project.BusTicketBooking.Application.Service.UserDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDetailServiceTest {

    @Mock
    private PassengerRepo passengerRepo;

    @InjectMocks
    private UserDetailService userDetailService;

    @BeforeEach
    public void setUp() {
        // Any additional setup can be done here if needed
    }

    @Test
    public void testLoadUserByUsername_Success() {
        // Arrange
        String email = "test@example.com";
        Passengers mockPassenger = new Passengers();
        mockPassenger.setEmail(email);
        // Mock the repository call
        when(passengerRepo.findByEmail(email)).thenReturn(mockPassenger);

        // Act
        UserDetails result = userDetailService.loadUserByUsername(email);

        // Assert
        assertNotNull(result, "UserDetails should not be null");
        assertEquals(email, result.getUsername(), "Email should match");
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        // Arrange
        String email = "nonexistent@example.com";
        // Mock the repository call to return null when the user is not found
        when(passengerRepo.findByEmail(email)).thenReturn(null);

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailService.loadUserByUsername(email);
        }, "Should throw UsernameNotFoundException when user is not found");
    }
}
