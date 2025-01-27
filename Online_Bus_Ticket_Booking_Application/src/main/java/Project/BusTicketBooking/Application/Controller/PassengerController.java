package Project.BusTicketBooking.Application.Controller;


import Project.BusTicketBooking.Application.DTO.UserDto;
import Project.BusTicketBooking.Application.Model.Passengers;
import Project.BusTicketBooking.Application.Service.UserDetails;
import Project.BusTicketBooking.Application.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class PassengerController {

    @Autowired
    UserDetailsService userDetailsService;

    //Connect to UserService Interface
    @Autowired
    private UserService userService;

    //Method to show Registration Form
    @GetMapping("/registration")
    public String getRegistrationForm(@ModelAttribute("user")UserDto userDto){
        return "register";
    }

    //Method to save Passengers
    @PostMapping("/registration")
    public String savePassenger(@ModelAttribute("user")UserDto userDto, Model model){
        userDto.setRole("USER");
        userService.save(userDto);
        model.addAttribute("message","Registered Successfully...!");
        return "register";
    }

    //Endpoint for Login Page
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    //Endpoint for User Page
    @GetMapping("/user")
    public String usersPage(Model model, Principal principal){
        UserDetails userDetails=(UserDetails) userDetailsService.loadUserByUsername(principal.getName());
        Passengers passengers=userDetails.getPassengers();
        model.addAttribute("passenger",passengers);
        return "passengerHome";
    }
}








