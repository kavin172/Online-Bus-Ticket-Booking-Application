package Project.BusTicketBooking.Application;

import Project.BusTicketBooking.Application.DTO.UserDto;
import Project.BusTicketBooking.Application.ExceptionHandler.InvalidUserInputException;
import Project.BusTicketBooking.Application.ExceptionHandler.PassengerNotFoundException;
import Project.BusTicketBooking.Application.Model.Passengers;
import Project.BusTicketBooking.Application.Repository.PassengerRepo;
import Project.BusTicketBooking.Application.Service.PassengerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class PassengerServiceTest {

    @Mock
    private PassengerRepo passengerRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private PassengerService passengerService;

    private Passengers mockPassenger;
    private UserDto mockUserDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize the UserDto mock object
        mockUserDto = new UserDto();
        mockUserDto.setName("John Doe");
        mockUserDto.setEmail("johndoe@example.com");
        mockUserDto.setPassword("password");
        mockUserDto.setRole("USER");

        // Initialize the Passengers object based on the UserDto
        mockPassenger = new Passengers();
        mockPassenger.setFullName(mockUserDto.getName());
        mockPassenger.setEmail(mockUserDto.getEmail());
        mockPassenger.setPassword(mockUserDto.getPassword());
        mockPassenger.setRole(mockUserDto.getRole());
    }

    @Test
    void testSavePassengerWithNullName() {
        // Set null name in the mock UserDto
        mockUserDto.setName(null);

        // Verify the exception handling for null name
        assertThrows(InvalidUserInputException.class, () -> {
            passengerService.save(mockUserDto);
        });
    }

    @Test
    void testSavePassengerWithNullEmail() {
        // Set null email in the mock UserDto
        mockUserDto.setEmail(null);

        // Verify the exception handling for null email
        assertThrows(InvalidUserInputException.class, () -> {
            passengerService.save(mockUserDto);
        });
    }

    @Test
    void testGetPassengerById() {
        // Mock the repository to return the mockPassenger when searched by ID
        when(passengerRepo.getByPassengerId(1L)).thenReturn(java.util.Optional.of(mockPassenger));

        // Call the service method
        Passengers foundPassenger = passengerService.getById(1L);

        // Verify interactions and assertions
        assertNotNull(foundPassenger);
        assertEquals("John Doe", foundPassenger.getFullName());
        assertEquals(null, foundPassenger.getPassengerId());
    }

    @Test
    void testGetPassengerByIdNotFound() {
        // Mock the repository to return an empty Optional when no passenger is found
        when(passengerRepo.getByPassengerId(99L)).thenReturn(java.util.Optional.empty());

        // Verify that PassengerNotFoundException is thrown
        assertThrows(PassengerNotFoundException.class, () -> {
            passengerService.getById(99L);
        });
    }
}
