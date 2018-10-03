import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ApplicationTest {

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
