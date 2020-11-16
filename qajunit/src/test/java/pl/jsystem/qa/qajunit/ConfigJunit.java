package pl.jsystem.qa.qajunit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class ConfigJunit {

    @BeforeEach
    public void setUpBeforEach() {
        System.out.println("---------- Befor each test -------------");
    }

    @BeforeAll
    public static void  setUpBeforAll() {
        System.out.println("---------- Befor all test -------------");
    }

    @AfterEach
    public void  setUpAfterEach() {
        System.out.println("---------- After each test -------------");
    }

    @AfterAll
    public static void  setUpAfterAll() {
        System.out.println("---------- After all test -------------");
    }
}
