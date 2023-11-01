package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpStatusCheckerTest {

  public static HttpStatusChecker httpStatusChecker;

    @BeforeAll
    static void beforeAll(){
        httpStatusChecker = new HttpStatusChecker();
    }

    @Test
    void testStatus100() throws Exception {
        assertEquals("https://http.cat/100.jpg", httpStatusChecker.getStatusImage(100));
    }

    @Test
    void testStatus200() throws Exception {
        assertEquals("https://http.cat/200.jpg", httpStatusChecker.getStatusImage(200));
    }

    @Test
    void testWrongStatus111() throws Exception {
        assertThrows(Exception.class, () -> httpStatusChecker.getStatusImage(111));
    }

    @Test
    void testWrongStatus1000() throws Exception {
        assertThrows(Exception.class, () -> httpStatusChecker.getStatusImage(1000));
    }
}