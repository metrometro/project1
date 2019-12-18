package com.epam.finalproject.util;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class PasswordEncoderTest {

    @Test
    public void testEncode() {
        String actualString = "admin";
        String expected = "d033e22ae348aeb5660fc2140aec35850c4da997";
        String actual = PasswordEncoder.encode(actualString);

        assertEquals(actual, expected);
    }
}