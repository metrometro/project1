package com.epam.finalproject.util;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class InputInfoValidatorTest {

    @Test
    public void testIsCardCodeValid() {
        String actualCodeNumber = "1111";
        boolean actual = InputInfoValidator.isCardCodeValid(actualCodeNumber);
        boolean expected = true;

        assertEquals(actual, expected);
    }

    @Test
    public void testIsCardCvcValid() {
        String actualCvcNumber = "111";
        boolean actual = InputInfoValidator.isCardCvcValid(actualCvcNumber);
        boolean expected = true;

        assertEquals(actual, expected);
    }

    @Test
    public void testIsLoginOrPasswordCorrect() {
        String loginOrPass = "111asd";
        boolean actual = InputInfoValidator.isLoginOrPasswordCorrect(loginOrPass);
        boolean expected = true;

        assertEquals(actual, expected);
    }

    @Test
    public void testIsPasswordsMatch() {
        String password = "111asd";
        String repeatedPassword = "111asd";
        boolean actual = InputInfoValidator.isPasswordsMatch(password, repeatedPassword);
        boolean expected = true;

        assertEquals(actual, expected);
    }

    @Test
    public void testIskNameCorrect() {
        String name = "Nikolas";
        boolean actual = InputInfoValidator.isLoginOrPasswordCorrect(name);
        boolean expected = true;

        assertEquals(actual, expected);
    }

    @Test
    public void testIsEmailCorrect() {
        String email = "twist31@gmail.com";
        boolean actual = InputInfoValidator.isEmailCorrect(email);
        boolean expected = true;

        assertEquals(actual, expected);
    }
}