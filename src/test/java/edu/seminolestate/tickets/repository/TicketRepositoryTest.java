package edu.seminolestate.tickets.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TicketRepositoryTest {

    @Autowired
    private TicketRepository repo;

    @Test
    void searchHandlesSingleQuoteSafely() {
        assertDoesNotThrow(() -> repo.search("'"));
    }
}