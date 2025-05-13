package crud2.Crud2.tests.security_tests;

import crud2.Crud2.security.JwtAuthenticationFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JwtHelperTest {

    private JwtAuthenticationFilter.JwtHelper jwtHelper;

    @BeforeEach
    void setUp() {
        jwtHelper = new JwtAuthenticationFilter.JwtHelper();  // Create an instance of JwtHelper
    }

    @Test
    void testGenerateAndExtractUsername() {
        UserDetails user = new User("Rahul", "password", new ArrayList<>());

        // Generate the token
        String token = jwtHelper.generateToken(user);
        Assertions.assertNotNull(token);

        // Extract the username from the generated token
        String extractedUsername = jwtHelper.getUsernameFromToken(token);
        Assertions.assertEquals("Rahul", extractedUsername);
    }

    @Test
    void testValidateToken() {
        UserDetails user = new User("Rahul", "password", new ArrayList<>());

        // Generate the token for the user
        String token = jwtHelper.generateToken(user);

        // Validate the generated token
        boolean isValid = jwtHelper.validateToken(token, user);
        assertTrue(isValid, "Token should be valid");
    }

    @Test
    void testInvalidTokenFailsValidation() {
        // Manually generate a valid token for "Rahul" using the JwtHelper
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername("Rahul")  // This username must match the one used to generate the token
                .password("password")
                .authorities(new ArrayList<>())
                .build();

        // Manually create a valid token using the same secret key (for testing purposes)
        String validToken = jwtHelper.generateToken(userDetails);

        // Modify the token (simulate an invalid token)
        String invalidToken = validToken + "invalid"; // Simulate a tampered token

        // Validate the invalid token
        boolean isValid = jwtHelper.validateToken(invalidToken, userDetails);

        // Ensure the invalid token is rejected
        assertFalse(isValid, "Token should be invalid");
    }
}
