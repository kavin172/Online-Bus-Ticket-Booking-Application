package Project.BusTicketBooking.Application.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDto {

    //Creating field from Passenger Class
    private String Name;
    private String email;
    private String password;
    private String role;

    //Constructor
    public UserDto(String Name, String email, String password, String role) {
        super();
        this.Name = Name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserDto() {

    }
}
