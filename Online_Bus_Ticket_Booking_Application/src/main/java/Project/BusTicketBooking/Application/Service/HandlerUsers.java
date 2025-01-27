package Project.BusTicketBooking.Application.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.io.IOException;

@Service
public class HandlerUsers implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        // Ensure that authentication and authorities are not null
        if (authentication == null || authentication.getAuthorities() == null) {
            try {
                throw new RoleNotFoundException("Authentication failed, no roles found.");
            } catch (RoleNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        // Handle roles
        var authorities = authentication.getAuthorities();
        var roles = authorities.stream()
                .map(r -> r.getAuthority())
                .findFirst();

        if (roles.isEmpty()) {
            try {
                throw new RoleNotFoundException("Authentication failed, user role is missing.");
            } catch (RoleNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        // Handle redirection based on roles
        if (roles.get().equals("ADMIN")) {
            response.sendRedirect("/admin");
        } else if (roles.get().equals("USER")) {
            response.sendRedirect("/user");
        } else {
            // Catch cases where the role is not recognized
            try {
                throw new RoleNotFoundException("Authentication failed, role '" + roles.get() + "' not recognized.");
            } catch (RoleNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
