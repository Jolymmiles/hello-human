import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMain {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    public TestMain() {
        Locale.setDefault(new Locale("en", "US"));
    }

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testMainFirst() {
        String data = "Valentin";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Main.main(null);
        String expected = "Hello, Valentin!";
        String actual = outContent.toString().replaceAll("[\\r\\n]", "");

        assertEquals(expected, actual);
    }

    @Test
    public void testMainSecond() {
        String data = "Ivanov1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Main.main(null);
        String expected = "Hello, Ivanov1!";
        String actual = outContent.toString().replaceAll("[\\r\\n]", "");

        assertEquals(expected, actual);
    }

}