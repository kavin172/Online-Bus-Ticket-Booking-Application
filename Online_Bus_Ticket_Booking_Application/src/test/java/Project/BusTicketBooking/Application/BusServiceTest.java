package Project.BusTicketBooking.Application;


import Project.BusTicketBooking.Application.Model.Bus;
import Project.BusTicketBooking.Application.Repository.BusRepo;
import Project.BusTicketBooking.Application.Service.BusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class BusServiceTest {

    @Mock
    private BusRepo busRepo;

    @InjectMocks
    private BusService busService;

    private Bus mockBus;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockBus = new Bus();
        mockBus.setBusId(1);
        mockBus.setBusName("Super Bus");
    }

    @Test
    void testGetBusById() {
        when(busRepo.findByBusId(1)).thenReturn(Optional.of(mockBus));

        Bus foundBus = busService.getBusById(1);

        assertNotNull(foundBus);
        assertEquals("Super Bus", foundBus.getBusName());
    }

    @Test
    void testSaveBus() {
        when(busRepo.save(mockBus)).thenReturn(mockBus);

        Bus savedBus = busService.saveBus(mockBus);

        assertNotNull(savedBus);
        assertEquals("Super Bus", savedBus.getBusName());
        verify(busRepo, times(1)).save(mockBus);
    }
}

