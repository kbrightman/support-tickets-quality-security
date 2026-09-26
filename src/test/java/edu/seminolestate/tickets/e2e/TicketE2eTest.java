package edu.seminolestate.tickets.e2e;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class TicketE2eTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    @Test
    void userCanSearchForTicket() {
        driver.get("http://localhost:8080/tickets");

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Printer");
        searchBox.submit();

        assertTrue(driver.getCurrentUrl().contains("q=Printer"));
    }
 
    @Test
    void ticketPageLoads() {
        driver.get("http://localhost:8080/tickets");

        assertEquals("Support Tickets", driver.getTitle());
    }
    
    @Test
    void userCanCreateValidTicket() {
        driver.get("http://localhost:8080/tickets/new");

        driver.findElement(By.name("requesterName")).sendKeys("Selenium User");
        driver.findElement(By.name("email")).sendKeys("selenium@example.com");
        driver.findElement(By.name("category")).sendKeys("Software");
        driver.findElement(By.name("description"))
                .sendKeys("Created by automated E2E test");

        driver.findElement(By.name("description")).submit();

        String pageText = driver.findElement(By.tagName("body")).getText();

        assertTrue(driver.getCurrentUrl().matches(".*/tickets/\\d+$"));
        assertTrue(pageText.contains("Selenium User"));
        assertTrue(pageText.contains("selenium@example.com"));
        assertTrue(pageText.contains("Created by automated E2E test"));
    }
}