package com.kylegosen.primenumbers;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ApplicationTest {

    @Test
    public void main_quit() throws Exception {
        setSystemInput("Q");

        ByteArrayOutputStream bo = mockSystemOut();
        String[] args = {};
        Application.main(args);
        assertTrue(getSystemOutString(bo).contains("Welcome to Prime Number Generator!"));
        assertTrue(getSystemOutString(bo).contains("Please enter a range of numbers to process (ex. 1-10) or 'Q' to quit:"));
        assertTrue(getSystemOutString(bo).contains("Thanks for playing!"));
    }

    @Test
    public void main_noInput() throws Exception {
        setSystemInput("\nQ");

        ByteArrayOutputStream bo = mockSystemOut();
        String[] args = {};
        Application.main(args);
        assertTrue(getSystemOutString(bo).contains("No input detected."));
    }

    @Test
    public void main_invalidInput() throws Exception {
        setSystemInput("123-abc\nQ");

        ByteArrayOutputStream bo = mockSystemOut();
        String[] args = {};
        Application.main(args);
        assertTrue(getSystemOutString(bo).contains("123-abc is not a valid range of numbers."));
    }

    @Test
    public void main_validInput() throws Exception {
        setSystemInput("1-10\nQ");

        ByteArrayOutputStream bo = mockSystemOut();
        String[] args = {};
        Application.main(args);
        assertTrue(getSystemOutString(bo).contains("Prime numbers: [2, 3, 5, 7]"));
    }

    @Test
    public void isValidInput_valid(){
        assertTrue(Application.isValidInput("1-10"));
        assertTrue(Application.isValidInput("10-1"));
        assertTrue(Application.isValidInput("01-0100"));
        assertTrue(Application.isValidInput("1-1"));
        assertTrue(Application.isValidInput("7900-7920"));
        assertTrue(Application.isValidInput("7920-7900"));
    }

    @Test
    public void isValidInput_notValid(){
        assertFalse(Application.isValidInput("a-b"));
        assertFalse(Application.isValidInput("1-b"));
        assertFalse(Application.isValidInput("a-1"));
        assertFalse(Application.isValidInput("-1-10"));
        assertFalse(Application.isValidInput("1--10"));
        assertFalse(Application.isValidInput("1"));
        assertFalse(Application.isValidInput(".1-10"));
        assertFalse(Application.isValidInput("1-.1"));
        assertFalse(Application.isValidInput(".1-.2"));
    }

    @Test
    public void processResult() throws Exception{
        ByteArrayOutputStream bo = mockSystemOut();
        Application.processResult(null);
        assertEquals("No prime numbers in the provided range!", getSystemOutString(bo));

        bo = mockSystemOut();
        Application.processResult(new ArrayList<>());
        assertEquals("No prime numbers in the provided range!", getSystemOutString(bo));

        bo = mockSystemOut();
        Application.processResult(Arrays.asList(2, 3, 5));
        assertEquals("Prime numbers: [2, 3, 5]", getSystemOutString(bo));
    }

    @Test
    public void getNumbersFromRange() {
        int[] numbers = Application.getNumbersFromRange("1-10");
        assertEquals(1, numbers[0]);
        assertEquals(10, numbers[1]);

        numbers = Application.getNumbersFromRange("10-1");
        assertEquals(1, numbers[0]);
        assertEquals(10, numbers[1]);

       numbers = Application.getNumbersFromRange("7900-7920");
        assertEquals(7900, numbers[0]);
        assertEquals(7920, numbers[1]);
    }

    // Helpers
    private void setSystemInput(String input){
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    private ByteArrayOutputStream mockSystemOut(){
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        return bo;
    }

    private String getSystemOutString(ByteArrayOutputStream bo) throws IOException{
        bo.flush();
        return new String(bo.toByteArray()).trim();
    }
}
