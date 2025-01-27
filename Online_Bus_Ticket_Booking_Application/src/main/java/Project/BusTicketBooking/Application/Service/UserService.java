package Project.BusTicketBooking.Application.Service;


import Project.BusTicketBooking.Application.DTO.UserDto;
import Project.BusTicketBooking.Application.Model.Passengers;

public interface UserService {

    //to collect the passenger information from Entity to Database
    Passengers save (UserDto userDto);
}
