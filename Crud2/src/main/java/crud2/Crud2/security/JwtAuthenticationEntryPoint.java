package crud2.Crud2.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
// This method will get anytime an unauthenticated user tries to access a secured endpoint

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Invalid or missing token / key");


         //    This is structured way and above is a simple way to add exceptions ie. Custom JSON body with ObjectMapper


//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.setContentType("application/json");
//
//        Map<String, Object> responseBody = new HashMap<>();
//        responseBody.put("error", "Unauthorized");
//        responseBody.put("message", "Invalid or missing token");
//        responseBody.put("status", 401);
//        responseBody.put("path", request.getRequestURI());
//
//        String jsonResponse = objectMapper.writeValueAsString(responseBody);
//        response.getWriter().write(jsonResponse);
    }
}

