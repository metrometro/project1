package com.epam.finalproject.util;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class XssSecurityTest {

    private String actualString;
    private String expected;

    @BeforeClass
    public void setUp() {
        actualString = "<test>";
        expected = "<test>";
    }

    @Test
    public void testProtectFromXssAttack() {
        String actual = XssSecurity.protectFromXssAttack(actualString);

        assertEquals(actual, expected);
    }

    @AfterClass
    public void tierDown() {
        actualString = null;
        expected = null;
    }
}