package edu.seminolestate.tickets.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TicketControllerTest {

    @Test
    void errorDoesNotExposeInternalDetails() {
        TicketController controller = new TicketController(null);

        String response = controller.error(
                new IllegalArgumentException("Sensitive internal information"));

        assertEquals(
                "An unexpected error occurred. Please try again later.",
                response);

        assertFalse(response.contains("IllegalArgumentException"));
        assertFalse(response.contains("Sensitive internal information"));
    }
}